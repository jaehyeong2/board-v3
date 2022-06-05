package jjfactory.boardtest.controller.board;

import jjfactory.boardtest.config.auth.PrincipalDetails;
import jjfactory.boardtest.domain.user.User;
import jjfactory.boardtest.dto.ApiResponse;
import jjfactory.boardtest.dto.board.BoardDto;
import jjfactory.boardtest.dto.board.FindBoardRes;
import jjfactory.boardtest.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/boards")
@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    @GetMapping("/{boardId}")
    public ApiResponse<FindBoardRes> findBoard(@PathVariable Long boardId){
        return new ApiResponse<>(boardService.findBoard(boardId));
    }

    @PostMapping("")
    public ApiResponse<String> createBoard(@RequestBody BoardDto dto, @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse<>(boardService.createBoard(dto,principal.getUser()));
    }
}
