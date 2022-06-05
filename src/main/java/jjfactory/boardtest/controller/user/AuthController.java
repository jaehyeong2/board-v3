package jjfactory.boardtest.controller.user;

import jjfactory.boardtest.dto.user.UserDto;
import jjfactory.boardtest.service.user.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    @GetMapping("/signup")
    public String signUpForm(){
        return "/auth/signup";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "/auth/login";
    }

    @ResponseBody
    @PostMapping("/signup")
    public void signUp(@RequestBody UserDto userDto){
        authService.signUp(userDto);
    }
}
