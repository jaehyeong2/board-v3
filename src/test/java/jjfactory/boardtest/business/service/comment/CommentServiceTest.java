package jjfactory.boardtest.business.service.comment;

import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.comment.Comment;
import jjfactory.boardtest.business.domain.comment.DeleteStatus;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.comment.req.CommentChange;
import jjfactory.boardtest.business.dto.comment.res.CommentResponse;
import jjfactory.boardtest.business.repository.board.BoardRepository;
import jjfactory.boardtest.business.repository.comment.CommentRepository;
import jjfactory.boardtest.business.repository.user.UserRepository;
import jjfactory.boardtest.global.dto.PagingResponse;
import jjfactory.boardtest.global.handler.ex.BusinessException;
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

    @Autowired
    UserRepository userRepository;

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
    @DisplayName("?????? ??????")
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
    @DisplayName("?????? ??????")
    void updateContent() {
        //given
        User user = User.builder().username("kim2").build();
        userRepository.save(user);

        Board board1 = Board.builder().title("title1").content("content1").build();
        Board board2 = Board.builder().title("title1").content("content1").build();

        boardRepository.saveAll(List.of(board1,board2));

        Comment comment = Comment.builder().board(board1).content("comment").user(user).build();
        commentRepository.save(comment);

        //when
        commentService.updateContent( new CommentChange("changed"),comment.getId(),user);

        //then
        assertThat(comment.getContent()).isEqualTo("changed");
    }

    @Test
    @DisplayName("?????? ????????? ??????")
    void commentLike() {
        //given
        User kim2 = User.builder().username("kim2").build();
        User wogud3 = User.builder().username("wogud3").build();
        userRepository.save(kim2);
        userRepository.save(wogud3);

        Board board1 = Board.builder().title("title1").content("content1").build();
        Board board2 = Board.builder().title("title1").content("content1").build();

        boardRepository.saveAll(List.of(board1,board2));

        Comment comment = Comment.builder().board(board1).content("comment").user(kim2).build();
        commentRepository.save(comment);

        //when
        commentService.commentLike(wogud3,comment.getId());

        //then
        assertThat(kim2.getActivePoint()).isEqualTo(1);
    }

    @Test
    @DisplayName("?????? ?????? ??????????????? ??????")
    void commentLikeException() {
        //given
        User kim2 = User.builder().username("kim2").build();
        User wogud3 = User.builder().username("wogud3").build();
        userRepository.save(kim2);
        userRepository.save(wogud3);

        Board board1 = Board.builder().title("title1").content("content1").build();
        Board board2 = Board.builder().title("title1").content("content1").build();

        boardRepository.saveAll(List.of(board1,board2));

        Comment comment = Comment.builder().board(board1).content("comment").user(kim2).build();
        commentRepository.save(comment);

        //expected
        assertThatThrownBy(() ->commentService.commentLike(kim2,comment.getId()))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("?????? ????????? ?????????")
    void commentDislike() {
        //given
        User kim2 = User.builder().username("kim2").build();
        User wogud3 = User.builder().username("wogud3").build();
        userRepository.save(kim2);
        userRepository.save(wogud3);

        Board board1 = Board.builder().title("title1").content("content1").build();
        Board board2 = Board.builder().title("title1").content("content1").build();

        boardRepository.saveAll(List.of(board1,board2));

        Comment comment = Comment.builder().board(board1).content("comment").user(kim2).build();
        commentRepository.save(comment);

        //when
        commentService.commentDislike(wogud3,comment.getId());

        //then
        assertThat(kim2.getActivePoint()).isEqualTo(-1);
    }

    @Test
    @DisplayName("?????? ?????? ??????????????? ?????????")
    void commentDislikeException() {
        //given
        User kim2 = User.builder().username("kim2").build();
        User wogud3 = User.builder().username("wogud3").build();
        userRepository.save(kim2);
        userRepository.save(wogud3);

        Board board1 = Board.builder().title("title1").content("content1").build();
        Board board2 = Board.builder().title("title1").content("content1").build();

        boardRepository.saveAll(List.of(board1,board2));

        Comment comment = Comment.builder().board(board1).content("comment").user(kim2).build();
        commentRepository.save(comment);

        //expected
        assertThatThrownBy(() ->commentService.commentDislike(kim2,comment.getId()))
                .isInstanceOf(BusinessException.class);
    }
}