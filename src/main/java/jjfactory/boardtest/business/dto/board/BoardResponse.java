package jjfactory.boardtest.business.dto.board;

import jjfactory.boardtest.business.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardResponse {
    private Long boardId;

    private String userName;
    private String title;
    private String content;
    private Long categoryId;
    private Boolean isView;
    private int likeCount;
    private String createDate;

    public BoardResponse(Board board) {
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.categoryId = board.getCategory().getId();
        this.isView = board.getIsView();
        this.likeCount = board.getLikeCount();
        this.createDate = board.getCreateDate().toString();
        this.userName = board.getUser().getName();
    }
}
