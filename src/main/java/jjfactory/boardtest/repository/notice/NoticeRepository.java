package jjfactory.boardtest.repository.notice;

import jjfactory.boardtest.domain.notice.Notice;
import jjfactory.boardtest.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
