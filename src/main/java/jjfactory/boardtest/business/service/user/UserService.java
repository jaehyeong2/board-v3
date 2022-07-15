package jjfactory.boardtest.business.service.user;

import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.user.res.FindUserRes;
import jjfactory.boardtest.business.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;



    public FindUserRes findUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        return new FindUserRes(user);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        user.withDraw();
    }
}
