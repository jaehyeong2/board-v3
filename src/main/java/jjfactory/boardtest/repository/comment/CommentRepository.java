package jjfactory.boardtest.repository.comment;

import jjfactory.boardtest.domain.comment.Comment;
import jjfactory.boardtest.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
