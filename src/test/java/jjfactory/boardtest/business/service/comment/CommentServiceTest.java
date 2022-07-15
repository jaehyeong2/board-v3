package jjfactory.boardtest.business.service.comment;

import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.comment.Comment;
import jjfactory.boardtest.business.domain.comment.DeleteStatus;
import jjfactory.boardtest.business.dto.comment.res.CommentResponse;
import jjfactory.boardtest.business.repository.board.BoardRepository;
import jjfactory.boardtest.business.repository.comment.CommentRepository;
import jjfactory.boardtest.global.dto.PagingResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    void findCommentsByBoardId() {
        //given
        Board board1 = Board.builder().title("title1").content("content1").build();
        Board board2 = Board.builder().title("title1").content("content1").build();

        boardRepository.saveAll(List.of(board1,board2));

        ArrayList<Comment> comments = new ArrayList<>();
        for (int i=0; i < 10; i++){
            Comment comment = Comment.builder().board(board1).content("comment" + i).build();
            comments.add(comment);
        }

        commentRepository.saveAll(comments);
        PagingResponse<CommentResponse> resultComments = commentService.findCommentsByBoardId(1, board1.getId());

        //expected
        assertThat(resultComments.getTotalCount()).isEqualTo(10);

    }

    @Test
    void createComment() {
    }

    @Test
    void deleteComment() {
        //given
        Board board1 = Board.builder().title("title1").content("content1").build();
        Board board2 = Board.builder().title("title1").content("content1").build();

        boardRepository.saveAll(List.of(board1,board2));

        Comment comment = Comment.builder().board(board1).content("comment").build();
        commentRepository.save(comment);

        //when
        commentService.deleteComment(comment.getId());

        //then
        assertThat(comment.getIsDeleted()).isEqualTo(DeleteStatus.DELETED);
    }

    @Test
    void updateContent() {
    }

    @Test
    @DisplayName("댓글 좋아요 테스트")
    void commentLike() {
    }

    @Test
    @DisplayName("댓글 싫어요 테스트")
    void commentDislike() {
    }
}