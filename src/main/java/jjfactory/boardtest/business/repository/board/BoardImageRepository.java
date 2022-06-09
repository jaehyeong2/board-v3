package jjfactory.boardtest.business.repository.board;

import jjfactory.boardtest.business.domain.board.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<BoardImage,Long> {
}
