package jjfactory.boardtest.repository.board;

import jjfactory.boardtest.domain.board.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<BoardImage,Long> {
}
