package jjfactory.boardtest.business.dto.board.req;

import jjfactory.boardtest.business.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardCreate {
    private String title;
    private String content;
    private Long categoryId;

    @Builder
    public BoardCreate(String title, String content, Long categoryId) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
    }

    public BoardCreate(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
