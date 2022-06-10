package jjfactory.boardtest.business.dto.board;

import jjfactory.boardtest.business.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardDto {
    private String title;
    private String content;
    private Long categoryId;

    public BoardDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}