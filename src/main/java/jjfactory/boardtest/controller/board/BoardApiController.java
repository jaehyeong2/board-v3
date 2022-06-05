package jjfactory.boardtest.controller.board;

import jjfactory.boardtest.dto.board.FindBoardRes;
import jjfactory.boardtest.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/boards")
@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    @GetMapping("/{boardId}")
    public ResponseEntity<FindBoardRes> findBoard(@PathVariable Long boardId){
        return new ResponseEntity<>(boardService.findBoard(boardId), HttpStatus.OK);
    }
}
