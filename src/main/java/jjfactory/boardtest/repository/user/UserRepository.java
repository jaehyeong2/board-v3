package jjfactory.boardtest.repository.user;

import jjfactory.boardtest.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
