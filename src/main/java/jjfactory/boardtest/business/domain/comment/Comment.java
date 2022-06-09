package jjfactory.boardtest.business.domain.comment;

import jjfactory.boardtest.business.domain.BaseTimeEntity;
import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.comment.CommentDto;
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
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Board board;

    private String content;

    private Boolean isView;

    private int likeCount;

    @Builder
    public Comment(User user, Board board, String content, Boolean isView,int likeCount) {
        this.user = user;
        this.board = board;
        this.content = content;
        this.isView = isView;
        this.likeCount = likeCount;
    }

    public static Comment create(CommentDto dto,User user,Board board){
        return builder()
                .content(dto.getContent())
                .user(user)
                .board(board)
                .isView(true)
                .likeCount(0)
                .build();
    }

    public void deleteComment() {
        isView = false;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void addLikeCount() {
        this.likeCount += 1;
    }

    public void subtractLikeCount() {
        this.likeCount -= 1;
    }
}
