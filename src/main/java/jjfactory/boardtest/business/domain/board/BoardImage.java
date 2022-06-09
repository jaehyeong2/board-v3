package jjfactory.boardtest.business.domain.board;

import jjfactory.boardtest.business.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class BoardImage extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String imageUrl;
    private Boolean isView;

    public BoardImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Builder
    public BoardImage(Board board, String imageUrl, Boolean isView) {
        this.board = board;
        this.imageUrl = imageUrl;
        this.isView = isView;
    }

    public static BoardImage create(Board board, String url){
        return builder()
                .board(board)
                .imageUrl(url)
                .isView(true)
                .build();
    }
}
