package jjfactory.boardtest.business.dto.board.res;

import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public BoardResponse(Board board, User user) {
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.categoryId = board.getCategory().getId();
        this.isView = board.getIsView();
        this.likeCount = board.getLikeCount();
        this.createDate = board.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.userName = user.getUsername();
    }
}
