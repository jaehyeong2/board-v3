package jjfactory.boardtest.business.dto.board;

import jjfactory.boardtest.business.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardResponse {
    private String title;
    private String content;
    private Long categoryId;
    private Boolean isView;
    private int likeCount;

    public BoardResponse(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.categoryId = board.getCategory().getId();
        this.isView = board.getIsView();
        this.likeCount = board.getLikeCount();
    }
}
