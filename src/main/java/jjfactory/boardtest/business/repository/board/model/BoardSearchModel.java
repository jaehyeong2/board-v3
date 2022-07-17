package jjfactory.boardtest.business.repository.board.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardSearchModel {
    private String title;
    private String content;
    private String username;

    @Builder
    public BoardSearchModel(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
}
