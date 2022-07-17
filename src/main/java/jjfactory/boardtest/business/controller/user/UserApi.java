package jjfactory.boardtest.business.controller.user;


import jjfactory.boardtest.business.dto.user.res.FindUserRes;
import jjfactory.boardtest.business.dto.user.res.UserInfoRes;
import jjfactory.boardtest.business.service.user.UserService;
import jjfactory.boardtest.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserApi {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<FindUserRes> findUserByUsername(@RequestParam String username){
        return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserInfoRes> getMyInfo(@PathVariable Long userId){
        return new ApiResponse<>(userService.getMyInfo(userId));
    }
}
