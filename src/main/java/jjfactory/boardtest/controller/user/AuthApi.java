package jjfactory.boardtest.controller.user;

import jjfactory.boardtest.dto.ApiResponse;
import jjfactory.boardtest.dto.user.LoginDto;
import jjfactory.boardtest.dto.user.TokenAndUserRes;
import jjfactory.boardtest.dto.user.UserDto;
import jjfactory.boardtest.service.user.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthApi {
    private final AuthService authService;

//    @ApiOperation("로그인")
    @PostMapping("/login")
    public ApiResponse<TokenAndUserRes> login(@RequestBody LoginDto dto){
        return new ApiResponse<>(authService.login(dto));
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody UserDto userDto){
        authService.signUp(userDto);
    }
}
