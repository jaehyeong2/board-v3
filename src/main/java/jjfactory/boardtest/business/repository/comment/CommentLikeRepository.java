package jjfactory.boardtest.business.repository.comment;

import jjfactory.boardtest.business.domain.comment.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike,Long> {
}
