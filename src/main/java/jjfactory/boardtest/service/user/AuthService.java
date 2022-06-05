package jjfactory.boardtest.service.user;

import jjfactory.boardtest.config.auth.TokenProvider;
import jjfactory.boardtest.domain.user.User;
import jjfactory.boardtest.dto.user.LoginDto;
import jjfactory.boardtest.dto.user.TokenAndUserRes;
import jjfactory.boardtest.dto.user.UserDto;
import jjfactory.boardtest.handler.ex.BusinessException;
import jjfactory.boardtest.handler.ex.ErrorCode;
import jjfactory.boardtest.repository.user.UserQueryRepository;
import jjfactory.boardtest.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;
    private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    public void signUp(UserDto userDto){
        String rawPassword = userDto.getPassword();
        String encPassword = encoder.encode(rawPassword);

        User user = User.createUser(userDto,encPassword);
        userRepository.save(user);
    }

    public TokenAndUserRes login(LoginDto dto){
        User user = userQueryRepository.findByUsername(dto.getUsername());
        matchPassword(dto.getPassword(),user.getPassword());
        String token = createToken(user);
        return new TokenAndUserRes(user,token);
    }


    public void matchPassword(String reqPassword,String userPassword){
        boolean result = passwordEncoder.matches(reqPassword,userPassword);
        if(!result){
            throw new BusinessException(ErrorCode.NOT_MATCH_PASSWORD);
        }
    }

    public String createToken(User user){
        List<String> roles = user.getRoles();
        Long id = user.getId();
        return tokenProvider.createToken(String.valueOf(user.getId()),user.getRoles());
    }
}
