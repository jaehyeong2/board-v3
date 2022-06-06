package jjfactory.boardtest.domain.board;

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
public class BoardLike extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Boolean state;

    @Builder
    public BoardLike(Board board, User user, Boolean state) {
        this.board = board;
        this.user = user;
        this.state = state;
    }

    public static BoardLike createLike(User user,Board board){
        return builder()
                .state(true)
                .user(user)
                .board(board)
                .build();
    }

    public static BoardLike createDislike(User user,Board board){
        return builder()
                .state(false)
                .user(user)
                .board(board)
                .build();
    }
}
