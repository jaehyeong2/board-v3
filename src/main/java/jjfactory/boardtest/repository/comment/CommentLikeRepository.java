package jjfactory.boardtest.repository.comment;

import jjfactory.boardtest.domain.comment.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike,Long> {
}
