package jjfactory.boardtest.business.dto.board.req;

import jjfactory.boardtest.business.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardCreate {
    private String title;
    private String content;
    private Long categoryId;

    public BoardCreate(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
