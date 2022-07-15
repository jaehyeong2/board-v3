package jjfactory.boardtest.business.dto.comment.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentCreate {
    private String content;
    private Long boardId;
}
