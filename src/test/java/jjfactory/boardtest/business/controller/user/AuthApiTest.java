package jjfactory.boardtest.business.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.user.req.LoginDto;
import jjfactory.boardtest.business.dto.user.req.UserDto;
import jjfactory.boardtest.business.repository.user.UserRepository;
import jjfactory.boardtest.business.service.user.AuthService;
import jjfactory.boardtest.global.handler.ex.BusinessException;
import jjfactory.boardtest.global.handler.ex.ErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class AuthApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("로그인 성공")
    void login() throws Exception {
        UserDto wogud2 = UserDto.builder().username("wogud2").password("1234").build();
        authService.signUp(wogud2);

        LoginDto loginRequest = new LoginDto("wogud2", "1234");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("패스워드 틀리면 로그인 실패")
    void loginFailed() throws Exception {

        UserDto wogud2 = UserDto.builder().username("wogud2").password("1234").build();
        authService.signUp(wogud2);

        LoginDto loginRequest = new LoginDto("wogud2", "12345");

        assertThatThrownBy(() -> mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
        ).hasCause(new BusinessException(ErrorCode.NOT_MATCH_PASSWORD));
    }

    @Test
    @DisplayName("회원가입 성공")
    void signUp() throws Exception {
        UserDto wogud2 = UserDto.builder().username("wogud2").password("1234").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wogud2))
                ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("중복아이디면 회원가입 실패")
    void signUpFailByDuplicateUsername() throws Exception {
        User user = User.builder().username("wogud2").password("123456").build();
        userRepository.save(user);

        UserDto wogud2 = UserDto.builder().username("wogud2").password("1234").build();


        assertThatThrownBy(() -> mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wogud2))
        ).andExpect(status().isOk())
        ).hasCause(new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID));
    }
}