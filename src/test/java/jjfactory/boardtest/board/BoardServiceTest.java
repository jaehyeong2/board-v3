package jjfactory.boardtest.board;

import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.board.Category;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.board.BoardDto;
import jjfactory.boardtest.business.dto.board.BoardResponse;
import jjfactory.boardtest.business.repository.board.BoardRepository;
import jjfactory.boardtest.business.repository.board.CategoryRepository;
import jjfactory.boardtest.business.repository.user.UserRepository;
import jjfactory.boardtest.business.service.board.BoardService;
import jjfactory.boardtest.global.dto.PagingResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardServiceTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardService boardService;

    @Autowired BoardRepository boardRepository;

    User user1;
    User user2;
    Category category;

    @Test
    @DisplayName("게시글 작성 유저 포인트 업 테스트")
    void point_up(){
        user1 = User.builder().name("lee").username("test11").build();
        category = Category.builder().name("공포").build();

        userRepository.save(user1);
        categoryRepository.save(category);

        User findUser = userRepository.findByUsername("test11");

        BoardDto boardDto = new BoardDto("게시글1","내용내용",category.getId());

        boardService.createBoard(boardDto,null,findUser.getId());
        assertThat(findUser.getActivePoint()).isEqualTo(3);

    }

    @Test
    @DisplayName("게시글 좋아요 포인트 업 테스트")
    void board_like(){
        user1 = User.builder().name("lee").username("test11").build();
        user2 = User.builder().name("kim").username("test22").build();
        category = Category.builder().name("공포").build();

        userRepository.save(user1);
        userRepository.save(user2);
        categoryRepository.save(category);
        BoardDto boardDto = new BoardDto("게시글1","내용내용",category.getId());

        Board board = Board.createBoard(boardDto, user1, category);
        boardRepository.save(board);

        boardService.boardLike(user2,board.getId());

        assertThat(board.getLikeCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글 싫어요 포인트 다운 테스트")
    void board_dislike(){
        user1 = User.builder().name("lee").username("test11").build();
        user2 = User.builder().name("kim").username("test22").build();
        category = Category.builder().name("공포").build();

        userRepository.save(user1);
        userRepository.save(user2);
        categoryRepository.save(category);
        BoardDto boardDto = new BoardDto("게시글1","내용내용",category.getId());

        Board board = Board.createBoard(boardDto, user1, category);
        boardRepository.save(board);

        boardService.boardDislike(user2,board.getId());

        assertThat(board.getLikeCount()).isEqualTo(-1);
    }

    @Test
    @DisplayName("페이징 카운트 테스트")
    void board_count(){
        user1 = User.builder().name("lee").username("test11").build();
        category = Category.builder().name("공포").build();

        userRepository.save(user1);
        categoryRepository.save(category);

        BoardDto boardDto = new BoardDto("게시글1","내용내용",category.getId());

        Board board = Board.createBoard(boardDto, user1, category);
        Board board2 = Board.createBoard(boardDto, user1, category);
        Board board3 = Board.createBoard(boardDto, user1, category);
        Board board4 = Board.createBoard(boardDto, user1, category);
        Board board5 = Board.createBoard(boardDto, user1, category);
        Board board6 = Board.createBoard(boardDto, user1, category);
        Board board7 = Board.createBoard(boardDto, user1, category);

        List<Board> boards = Arrays.asList(board,board2,board3,board4,board5,board6,board7);

        boardRepository.saveAll(boards);

        PagingResponse<BoardResponse> result = boardService.findBoards(1, null);
        PagingResponse<BoardResponse> result2 = boardService.findBoards(2, null);

        assertThat(result.getTotalCount()).isEqualTo(7);
        assertThat(result2.getTotalCount()).isEqualTo(0);
    }
}
