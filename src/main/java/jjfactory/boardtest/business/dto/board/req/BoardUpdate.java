package jjfactory.boardtest.business.dto.board.req;

import jjfactory.boardtest.business.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardUpdate {
    private String content;
    private String title;

    public BoardUpdate(Board board) {
        this.content = board.getContent();
        this.title = board.getTitle();
    }
}
