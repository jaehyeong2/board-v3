package jjfactory.boardtest.service.user;

import jjfactory.boardtest.domain.user.User;
import jjfactory.boardtest.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        user.withDraw();
    }
}
