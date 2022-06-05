package jjfactory.boardtest.service.user;

import jjfactory.boardtest.domain.user.User;
import jjfactory.boardtest.dto.user.UserDto;
import jjfactory.boardtest.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void signUp(UserDto userDto){
        String rawPassword = userDto.getPassword();
        String encPassword = encoder.encode(rawPassword);

        User user = User.createUser(userDto,encPassword);
        userRepository.save(user);
    }

}
