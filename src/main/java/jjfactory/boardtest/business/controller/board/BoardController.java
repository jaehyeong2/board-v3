package jjfactory.boardtest.business.controller.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
public class BoardController {

    @GetMapping(value = "/boards/test")
    public String boards(){
        return "board/list";
    }
}
