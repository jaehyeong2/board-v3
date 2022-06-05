package jjfactory.boardtest.domain.comment;

import jjfactory.boardtest.domain.BaseTimeEntity;
import jjfactory.boardtest.domain.board.Board;
import jjfactory.boardtest.domain.user.User;
import jjfactory.boardtest.dto.comment.CommentDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String content;

    private Boolean isView;

    @Builder
    public Comment(User user, Board board, String content, Boolean isView) {
        this.user = user;
        this.board = board;
        this.content = content;
        this.isView = isView;
    }

    public static Comment create(CommentDto dto,User user,Board board){
        return builder()
                .content(dto.getContent())
                .user(user)
                .board(board)
                .isView(true)
                .build();
    }

    public void deleteComment() {
        isView = false;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
