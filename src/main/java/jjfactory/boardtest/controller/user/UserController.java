package jjfactory.boardtest.controller.user;


import jjfactory.boardtest.dto.board.FindBoardRes;
import jjfactory.boardtest.dto.user.FindUserRes;
import jjfactory.boardtest.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<FindUserRes> findUser(@RequestParam String username){
        return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
    }
}
