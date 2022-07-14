package jjfactory.boardtest.business.service.board;

import jjfactory.boardtest.business.repository.board.*;
import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.board.BoardImage;
import jjfactory.boardtest.business.domain.board.BoardLike;
import jjfactory.boardtest.business.domain.board.Category;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.global.dto.MyPageRequest;
import jjfactory.boardtest.global.dto.PagingResponse;
import jjfactory.boardtest.business.dto.board.BoardDto;
import jjfactory.boardtest.business.dto.board.BoardResponse;
import jjfactory.boardtest.business.dto.board.BoardUpdateReq;
import jjfactory.boardtest.business.dto.board.BoardDetailRes;
//import jjfactory.boardtest.repository.board.*;
import jjfactory.boardtest.business.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {
    private final int BOARD_WRITE_POINT = 3;
    private final int LIKE_POINT = 3;
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final BoardQueryRepository boardQueryRepository;
    private final BoardImageRepository imageRepository;
    private final BoardLikeRepository likeRepository;
    private final UserRepository userRepository;
    @Transactional(readOnly = true)
    public BoardDetailRes findBoard(Long id){
        Board board = getBoard(id);
        board.viewCountUp();
        return new BoardDetailRes(board);
    }
    @Transactional(readOnly = true)
    public PagingResponse<BoardResponse> findBoards(int page,String query){
        Pageable pageRequest = new MyPageRequest(page,10).of();
        Page<BoardResponse> boards = boardQueryRepository.findAllBoards(pageRequest);
        return new PagingResponse<>(boards);
    }

    @Transactional(readOnly = true)
    public PagingResponse<BoardResponse> findBoardsByCategoryId(int page,String query,Long categoryId){
        Pageable pageRequest = new MyPageRequest(page,10).of();
        Page<BoardResponse> boards = boardQueryRepository.findBoardsByCategory(pageRequest,categoryId);
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
        List<BoardImage> imageList = new ArrayList<>();
        boardRepository.save(board);
//        images.forEach(i -> {
//            String folderPath = "/images/board";
//            String filePath = folderPath + i.getOriginalFilename();
//
//            BoardImage.create(board,filePathe)
//        });
        pointUpByBoardWrite(user);
        return "y";
    }

    private void pointUpByBoardWrite(User user) {
        user.pointUp(BOARD_WRITE_POINT);
    }

    public String deleteBoard(Long id){
        Board board = getBoard(id);
        board.delete();
        return "Y";
    }

    public String updateBoard(BoardUpdateReq dto, Long id){
        Board board = getBoard(id);

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
        writer.pointUp(LIKE_POINT);
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
        writer.pointDown(LIKE_POINT);
        return "Y";
    }

    private Board getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });
        return board;
    }
}
