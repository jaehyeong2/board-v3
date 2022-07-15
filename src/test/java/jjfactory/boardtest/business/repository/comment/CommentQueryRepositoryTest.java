package jjfactory.boardtest.business.repository.comment;

import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.comment.Comment;
import jjfactory.boardtest.business.repository.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CommentQueryRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentQueryRepository commentQueryRepository;

    @Test
    void findComments() {
        Board board1 = Board.builder().title("title1").content("content1").build();
        Board board2 = Board.builder().title("title2").content("content2").build();

        boardRepository.saveAll(List.of(board1,board2));

        List<Comment> comments = IntStream.range(1, 21).mapToObj(
                i -> Comment.builder()
                        .board(board1)
                        .content("comment i")
                        .build()).collect(Collectors.toList());

        commentRepository.saveAll(comments);

    }

    @Test
    void testFindComments() {
        Board board1 = Board.builder().title("title1").content("content1").build();
        Board board2 = Board.builder().title("title2").content("content2").build();

        boardRepository.saveAll(List.of(board1,board2));

        List<Comment> comments = IntStream.range(1, 21).mapToObj(
                i -> Comment.builder()
                        .board(board1)
                        .content("comment i")
                        .build()).collect(Collectors.toList());

        commentRepository.saveAll(comments);
    }
}