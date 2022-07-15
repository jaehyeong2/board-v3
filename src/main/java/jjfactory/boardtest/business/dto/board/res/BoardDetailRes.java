package jjfactory.boardtest.business.dto.board.res;

import jjfactory.boardtest.business.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardDetailRes {

    private Long boardId;

    private String userName;
    private String title;
    private String content;

    public BoardDetailRes(Board board) {
        this.boardId = board.getId();
        this.userName = board.getUser().getName();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
