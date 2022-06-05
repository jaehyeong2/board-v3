package jjfactory.boardtest.repository.board;

import jjfactory.boardtest.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
