package jjfactory.boardtest.repository.board;

import jjfactory.boardtest.domain.board.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike,Long> {
}
