package jjfactory.boardtest.domain.board;

import jjfactory.boardtest.domain.BaseTimeEntity;
import jjfactory.boardtest.domain.user.User;
import jjfactory.boardtest.dto.board.BoardDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Board extends BaseTimeEntity{
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<BoardImage> imageList = new ArrayList<>();

    @Column(length = 100)
    private String title;
    private String content;

    private Boolean isView;

    private int likeCount;

    @Builder
    public Board(User user, String title, String content, Boolean isView,int likeCount) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.isView = isView;
        this.likeCount = likeCount;
    }

    public static Board createBoard(BoardDto dto,User user){
        return builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .likeCount(0)
                .isView(true)
                .build();
    }

    public void addImage(BoardImage image) {
        this.imageList.add(image);
    }

    public void deleteBoard() {
        isView = false;
    }
}
