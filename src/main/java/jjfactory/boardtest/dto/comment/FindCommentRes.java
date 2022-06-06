package jjfactory.boardtest.dto.comment;

import jjfactory.boardtest.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindCommentRes {
    private String content;

    public FindCommentRes(Comment comment) {
        this.content = comment.getContent();
    }
}
