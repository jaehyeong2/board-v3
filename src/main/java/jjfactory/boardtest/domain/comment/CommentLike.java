package jjfactory.boardtest.domain.comment;

import jjfactory.boardtest.domain.BaseTimeEntity;
import jjfactory.boardtest.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CommentLike extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Boolean state;

    @Builder
    public CommentLike(Comment comment, User user, Boolean state) {
        this.comment = comment;
        this.user = user;
        this.state = state;
    }

    public static CommentLike createLike(User user, Comment comment){
        return builder()
                .state(true)
                .user(user)
                .comment(comment)
                .build();
    }

    public static CommentLike createDislike(User user,Comment comment){
        return builder()
                .state(false)
                .user(user)
                .comment(comment)
                .build();
    }
}
