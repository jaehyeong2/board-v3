package jjfactory.boardtest.business.repository.user;

import jjfactory.boardtest.business.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
//    Optional<User> findByUsername(String username);
    User findByUsername(String username);
    User findByEmail(String email);
}
