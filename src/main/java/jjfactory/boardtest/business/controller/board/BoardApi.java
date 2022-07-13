package jjfactory.boardtest.business.controller.board;

import jjfactory.boardtest.global.config.auth.PrincipalDetails;
import jjfactory.boardtest.global.dto.ApiPagingResponse;
import jjfactory.boardtest.global.dto.ApiResponse;
import jjfactory.boardtest.business.dto.board.BoardDto;
import jjfactory.boardtest.business.dto.board.BoardResponse;
import jjfactory.boardtest.business.dto.board.BoardUpdateReq;
import jjfactory.boardtest.business.dto.board.BoardDetailRes;
import jjfactory.boardtest.business.dto.comment.CommentResponse;
import jjfactory.boardtest.business.service.board.BoardService;
import jjfactory.boardtest.business.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/boards")
@RequiredArgsConstructor
@RestController
public class BoardApi {
    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/{boardId}")
    public ApiResponse<BoardDetailRes> getBoard(@PathVariable Long boardId){
        return new ApiResponse<>(boardService.findBoard(boardId));
    }

    @GetMapping("")
    public ApiPagingResponse<BoardResponse> getBoards(@RequestParam(defaultValue = "1", required = false, name = "page") int page,
                                                      @RequestParam(required = false) String query){
        return new ApiPagingResponse<>(boardService.findBoards(page,query));
    }

    @GetMapping("/category/{categoryId}")
    public ApiPagingResponse<BoardResponse> getBoardsByCategory(@RequestParam(defaultValue = "1", required = false, name = "page") int page,
                                                                @RequestParam(required = false) String query,
                                                                @PathVariable Long categoryId){
        return new ApiPagingResponse<>(boardService.findBoardsByCategoryId(page,query,categoryId));
    }

    @GetMapping("/{boardId}/comments")
    public ApiPagingResponse<CommentResponse> getCommentsByBoardId(@RequestParam(required = false, defaultValue = "1") int page,
                                                                   @RequestParam(required = false) String query,
                                                                   @RequestParam(required = false) Long boardId){
        return new ApiPagingResponse<>(commentService.findCommentsByBoardId(page,boardId));
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
