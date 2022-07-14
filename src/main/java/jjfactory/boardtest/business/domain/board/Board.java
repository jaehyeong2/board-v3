package jjfactory.boardtest.business.domain.board;

import jjfactory.boardtest.business.domain.BaseTimeEntity;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.board.BoardDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Board extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<BoardImage> imageList = new ArrayList<>();

    @Comment("제목")
    @Column(length = 100)
    private String title;
    @Comment("내용")
    private String content;

    @Comment("활성화 여부: 활성화 / 숨김")
    private Boolean isView;

    @Comment("조회수")
    private int viewCount;

    @Comment("좋아요 갯수")
    private int likeCount;

    @Builder
    public Board(User user, Category category, List<BoardImage> imageList, String title, String content, Boolean isView,int viewCount, int likeCount) {
        this.user = user;
        this.category = category;
        this.imageList = imageList;
        this.title = title;
        this.content = content;
        this.isView = isView;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
    }

    public static Board createBoard(BoardDto dto, User user, Category category){
        return builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .category(category)
                .likeCount(0)
                .viewCount(0)
                .isView(true)
                .build();
    }

    public void addImage(BoardImage image) {
        this.imageList.add(image);
    }

    public void updateBoard(String title,String content) {
        this.title = title;
        this.content = content;
    }

    public void delete() {
        isView = false;
    }

    public void addLikeCount() {
        this.likeCount += 1;
    }

    public void subtractLikeCount() {
        this.likeCount -= 1;
    }

    public void viewCountUp() {
        this.viewCount += 1;
    }
}
