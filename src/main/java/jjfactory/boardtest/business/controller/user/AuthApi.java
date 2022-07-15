package jjfactory.boardtest.business.controller.user;

import jjfactory.boardtest.global.dto.ApiResponse;
import jjfactory.boardtest.business.dto.user.req.LoginDto;
import jjfactory.boardtest.business.dto.user.res.TokenAndUserRes;
import jjfactory.boardtest.business.dto.user.req.UserDto;
import jjfactory.boardtest.business.service.user.AuthService;
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
    public ApiResponse<String> signUp(@RequestBody UserDto userDto){
        return new ApiResponse<>(authService.signUp(userDto));
    }
}
