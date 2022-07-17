package jjfactory.boardtest.business.service.user;

import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.user.res.FindUserRes;
import jjfactory.boardtest.business.dto.user.res.UserInfoRes;
import jjfactory.boardtest.business.repository.user.UserRepository;
import jjfactory.boardtest.global.handler.ex.BusinessException;
import jjfactory.boardtest.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserInfoRes getMyInfo(Long userId){
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        return new UserInfoRes(user);
    }

    @Transactional(readOnly = true)
    public FindUserRes findUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null) throw new BusinessException(ErrorCode.NOT_FOUND_USER);
        return new FindUserRes(user);
    }

    public String deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        user.withDraw();
        return "Y";
    }
}
