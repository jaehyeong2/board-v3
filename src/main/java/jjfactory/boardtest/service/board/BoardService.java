package jjfactory.boardtest.service.board;

import jjfactory.boardtest.domain.board.Board;
import jjfactory.boardtest.domain.board.BoardLike;
import jjfactory.boardtest.domain.board.Category;
import jjfactory.boardtest.domain.user.User;
import jjfactory.boardtest.dto.MyPageRequest;
import jjfactory.boardtest.dto.PagingResponse;
import jjfactory.boardtest.dto.board.BoardDto;
import jjfactory.boardtest.dto.board.BoardResponse;
import jjfactory.boardtest.dto.board.BoardUpdateReq;
import jjfactory.boardtest.dto.board.FindBoardRes;
import jjfactory.boardtest.repository.board.*;
import jjfactory.boardtest.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {

    private final int BOARD_WRITE_POINT = 5;
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final BoardQueryRepository boardQueryRepository;
    private final BoardImageRepository imageRepository;
    private final BoardLikeRepository likeRepository;
    private final UserRepository userRepository;
    @Transactional(readOnly = true)
    public FindBoardRes findBoard(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });
        return new FindBoardRes(board);
    }
    @Transactional(readOnly = true)
    public PagingResponse<BoardResponse> findBoards(int page){
        Pageable pageRequest = new MyPageRequest(page,10).of();
        Page<BoardResponse> boards = boardQueryRepository.findAll(pageRequest);
        return new PagingResponse<>(boards);
    }

    public String createBoard(BoardDto dto, List<MultipartFile> images, Long userId){
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        // userRepository에서 찾아오지 않고 Principaldetails에서 바로 getUser해오면, 포인트 변경이 감지가 안됨.
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        Board board = Board.createBoard(dto, user,category);
        boardRepository.save(board);
        pointUpByBoardWrite(user);
        return "y";
    }

    private void pointUpByBoardWrite(User user) {
        user.pointUp(BOARD_WRITE_POINT);
    }

    public String deleteBoard(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        board.deleteBoard();
        return "Y";
    }

    public String updateBoard(BoardUpdateReq dto, Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        board.updateBoard(dto.getTitle(), dto.getContent());
        return "Y";
    }

    public String boardLike(User user,Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });

        BoardLike like = BoardLike.createLike(user, board);
        likeRepository.save(like);
        board.addLikeCount();
        User writer = board.getUser();
        writer.pointUp(5);
        return "Y";
    }

    public String boardDislike(User user,Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });

        BoardLike like = BoardLike.createDislike(user, board);
        likeRepository.save(like);
        board.subtractLikeCount();
        User writer = board.getUser();
        writer.pointDown(5);
        return "Y";
    }
}
