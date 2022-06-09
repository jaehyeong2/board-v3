package jjfactory.boardtest.business.service.user;

import jjfactory.boardtest.global.config.auth.TokenProvider;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.user.LoginDto;
import jjfactory.boardtest.business.dto.user.TokenAndUserRes;
import jjfactory.boardtest.business.dto.user.UserDto;
import jjfactory.boardtest.global.handler.ex.BusinessException;
import jjfactory.boardtest.global.handler.ex.ErrorCode;
import jjfactory.boardtest.business.repository.user.UserQueryRepository;
import jjfactory.boardtest.business.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;
    private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    public String signUp(UserDto userDto){
        usernameDuplicateCheck(userDto);
        String rawPassword = userDto.getPassword();
        String encPassword = encoder.encode(rawPassword);

        User user = User.createUser(userDto,encPassword);
        userRepository.save(user);
        return "Y";
    }

    private void usernameDuplicateCheck(UserDto userDto) {
        if(userRepository.findByUsername(userDto.getUsername()) != null){
            throw new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
        }
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
        return tokenProvider.createToken(user.getUsername(),user.getRoles());
    }
}
