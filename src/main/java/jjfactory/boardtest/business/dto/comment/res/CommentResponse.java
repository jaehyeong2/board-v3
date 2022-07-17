package jjfactory.boardtest.business.dto.comment.res;

import jjfactory.boardtest.business.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentResponse {
    private Long commentId;
    private String username;
    private String content;
    private int likeCount;
    private LocalDateTime createDate;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getId();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
        this.createDate = comment.getCreateDate();
    }
}
