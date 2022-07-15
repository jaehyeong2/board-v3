package jjfactory.boardtest.business.service.user;

import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.user.res.FindUserRes;
import jjfactory.boardtest.business.repository.user.UserRepository;
import jjfactory.boardtest.global.handler.ex.BusinessException;
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
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    @DisplayName("이름으로 찾기 테스트 성공")
    void findUserByUsername() {
        //given
        User jay = User.builder().username("jay").build();
        User kim = User.builder().username("kim").build();
        userRepository.save(jay);
        userRepository.save(kim);

        //when
        long count = userRepository.count();
        FindUserRes findUser = userService.findUserByUsername("kim");

        //then
        assertThat(count).isEqualTo(2);
        assertThat(findUser.getUsername()).isEqualTo("kim");
    }

    @Test
    @DisplayName("이름으로 찾기 테스트 예외")
    void findUserByUsernameException() {
        //given
        User jay = User.builder().username("jay").build();
        User kim = User.builder().username("kim").build();
        userRepository.save(jay);
        userRepository.save(kim);

        //expected
        assertThatThrownBy(() -> userService.findUserByUsername("jay2"))
                .isInstanceOf(BusinessException.class);

    }

    @Test
    @DisplayName("유저 삭제 테스트")
    void deleteUser() {
        //given
        User jay = User.builder().username("jay").build();
        User kim = User.builder().username("kim").build();
        userRepository.save(jay);
        userRepository.save(kim);

        //when
        userService.deleteUser(jay.getId());
        User findJay = userRepository.findByUsername("jay");

        //then
        assertThat(findJay.getActiveState()).isEqualTo(false);
    }
}