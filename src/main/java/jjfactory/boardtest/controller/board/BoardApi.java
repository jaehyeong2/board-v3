package jjfactory.boardtest.controller.board;

import jjfactory.boardtest.config.auth.PrincipalDetails;
import jjfactory.boardtest.dto.ApiPagingResponse;
import jjfactory.boardtest.dto.ApiResponse;
import jjfactory.boardtest.dto.board.BoardDto;
import jjfactory.boardtest.dto.board.BoardResponse;
import jjfactory.boardtest.dto.board.BoardUpdateReq;
import jjfactory.boardtest.dto.board.FindBoardRes;
import jjfactory.boardtest.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/boards")
@RequiredArgsConstructor
@RestController
public class BoardApi {
    private final BoardService boardService;

    @GetMapping("/{boardId}")
    public ApiResponse<FindBoardRes> findBoard(@PathVariable Long boardId){
        return new ApiResponse<>(boardService.findBoard(boardId));
    }

    @GetMapping("")
    public ApiPagingResponse<BoardResponse> findBoards(@RequestParam(defaultValue = "1", required = false, name = "page") int page,
                                                       @RequestParam(required = false) String query){
        return new ApiPagingResponse<>(boardService.findBoards(page));
    }

    @PostMapping("")
    public ApiResponse<String> createBoard(@RequestBody BoardDto dto,
                                           @RequestParam(required = false) List<MultipartFile> images,
                                           @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse<>(boardService.createBoard(dto,images,principal.getUser().getId()));
    }

    @PatchMapping("/{id}")
    public ApiResponse<String> updateBoard(@RequestBody BoardUpdateReq dto,
                                           @PathVariable Long id,
                                           @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse<>(boardService.updateBoard(dto,id));
    }

    @PostMapping("/{boardId}/like")
    public ApiResponse<String> likeBoard(@PathVariable Long boardId,
                                         @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse<>(boardService.boardLike(principal.getUser(),boardId));
    }

    @PostMapping("/{boardId}/dislike")
    public ApiResponse<String> dislikeBoard(@PathVariable Long boardId,
                                         @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse<>(boardService.boardDislike(principal.getUser(),boardId));
    }
}
