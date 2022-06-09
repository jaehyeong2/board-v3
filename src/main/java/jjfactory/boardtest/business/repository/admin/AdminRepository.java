package jjfactory.boardtest.business.repository.admin;

import jjfactory.boardtest.business.domain.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
