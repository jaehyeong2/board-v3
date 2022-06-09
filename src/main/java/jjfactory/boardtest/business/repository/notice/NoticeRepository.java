package jjfactory.boardtest.business.repository.notice;

import jjfactory.boardtest.business.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
