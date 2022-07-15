package jjfactory.boardtest.business.dto.comment.res;

import jjfactory.boardtest.business.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentResponse {
    private Long boardId;
    private Long userId;
    private String content;
    private int likeCount;

    public CommentResponse(Comment comment) {
        this.boardId = comment.getBoard().getId();
        this.userId = comment.getUser().getId();
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
    }
}
