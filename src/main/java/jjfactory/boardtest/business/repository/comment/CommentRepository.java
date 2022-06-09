package jjfactory.boardtest.business.repository.comment;

import jjfactory.boardtest.business.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
