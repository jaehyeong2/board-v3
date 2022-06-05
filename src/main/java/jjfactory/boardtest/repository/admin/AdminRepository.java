package jjfactory.boardtest.repository.admin;

import jjfactory.boardtest.domain.admin.Admin;
import jjfactory.boardtest.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
