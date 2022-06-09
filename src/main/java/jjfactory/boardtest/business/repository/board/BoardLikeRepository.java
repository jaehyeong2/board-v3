package jjfactory.boardtest.business.repository.board;

import jjfactory.boardtest.business.domain.board.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike,Long> {
}
