package jjfactory.boardtest.repository.notice;

import jjfactory.boardtest.domain.board.BoardImage;
import jjfactory.boardtest.domain.notice.NoticeImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeImageRepository extends JpaRepository<NoticeImage,Long> {
}
