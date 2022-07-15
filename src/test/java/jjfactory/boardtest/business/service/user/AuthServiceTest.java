package jjfactory.boardtest.business.service.user;

import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.user.req.LoginDto;
import jjfactory.boardtest.business.dto.user.req.UserDto;
import jjfactory.boardtest.business.dto.user.res.TokenAndUserRes;
import jjfactory.boardtest.business.repository.user.UserRepository;
import jjfactory.boardtest.global.handler.ex.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class AuthServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @Test
    @DisplayName("회원가입")
    void signUp() {
        //given
        UserDto jay = UserDto.builder().username("jay").password("1234").build();
        authService.signUp(jay);

        //when
        List<User> users = userRepository.findAll();
        List<String> userNames = users.stream().map(u -> u.getUsername()).collect(Collectors.toList());

        //then
        assertThat(userRepository.count()).isEqualTo(1);
        assertThat(userNames).contains("jay");
    }

    @Test
    @DisplayName("회원가입 아디디 중복이면 익셉션")
    void signUpException() {
        //given
        User jay = User.builder().username("jay").build();
        userRepository.save(jay);

        UserDto jayDto = UserDto.builder().username("jay").password("1234").build();

        //expected
        assertThatThrownBy(() -> authService.signUp(jayDto) )
                .isInstanceOf(BusinessException.class);
    }

    @Test
    @DisplayName("로그인")
    void login() {
        //given
        UserDto jay = UserDto.builder().username("jay").password("1234").build();

        authService.signUp(jay);

        //when
        TokenAndUserRes res = authService.login(new LoginDto("jay", "1234"));

        String token = res.getToken();
        String username = res.getUsername();

        //then
        assertThat(token).isNotNull();
        assertThat(username).isEqualTo("jay");
    }

    @Test
    @DisplayName("비밀번호 틀리면 로그인 실패")
    void loginFailed() {
        //given
        UserDto jay = UserDto.builder().username("jay").password("1234").build();

        authService.signUp(jay);

        //expected
        assertThatThrownBy(() -> authService.login(new LoginDto("jay", "12345")))
                .isInstanceOf(BusinessException.class);

    }
}