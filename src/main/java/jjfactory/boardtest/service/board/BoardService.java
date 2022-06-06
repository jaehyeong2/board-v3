package jjfactory.boardtest.service.board;

import jjfactory.boardtest.domain.board.Board;
import jjfactory.boardtest.domain.user.User;
import jjfactory.boardtest.dto.board.BoardDto;
import jjfactory.boardtest.dto.board.FindBoardRes;
import jjfactory.boardtest.repository.board.BoardImageRepository;
import jjfactory.boardtest.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardImageRepository imageRepository;

    @Transactional(readOnly = true)
    public FindBoardRes findBoard(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });
        return new FindBoardRes(board);
    }

    public String createBoard(BoardDto dto, List<MultipartFile> images, User user){
        user.pointUp(5);
        Board board = Board.createBoard(dto, user);
        boardRepository.save(board);

        return "y";
    }

    public String deleteBoard(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        board.deleteBoard();
        return "Y";
    }

    public void updateTitle(Long id){

    }
}
