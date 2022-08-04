package jjfactory.boardtest.business.repository.user;

import jjfactory.boardtest.business.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("h2 연결테스트")
    void h2DbTest(){
        User user1 = User.builder().name("user1").build();
        User user2 = User.builder().name("user2").build();

        userRepository.save(user2);
        userRepository.save(user1);

        assertThat(userRepository.count()).isEqualTo(2);
    }
}