package jjfactory.boardtest.business.dto.board;

import jjfactory.boardtest.business.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardUpdateReq {
    private String content;
    private String title;

    public BoardUpdateReq(Board board) {
        this.content = board.getContent();
        this.title = board.getTitle();
    }
}
