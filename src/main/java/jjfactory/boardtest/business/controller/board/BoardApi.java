package jjfactory.boardtest.business.controller.board;

import jjfactory.boardtest.business.repository.board.model.BoardSearchModel;
import jjfactory.boardtest.global.config.auth.PrincipalDetails;
import jjfactory.boardtest.global.dto.ApiPagingResponse;
import jjfactory.boardtest.global.dto.ApiResponse;
import jjfactory.boardtest.business.dto.board.req.BoardCreate;
import jjfactory.boardtest.business.dto.board.res.BoardResponse;
import jjfactory.boardtest.business.dto.board.req.BoardUpdate;
import jjfactory.boardtest.business.dto.board.res.BoardDetailRes;
import jjfactory.boardtest.business.dto.comment.res.CommentResponse;
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
                                                      @RequestParam(required = false) String title,
                                                      @RequestParam(required = false) String content,
                                                      @RequestParam(required = false) String username){
        return new ApiPagingResponse<>(boardService.findBoards(page,
                BoardSearchModel
                        .builder()
                        .title(title)
                        .content(content)
                        .username(username)
                        .build()));
    }

    @GetMapping("/category/{categoryId}")
    public ApiPagingResponse<BoardResponse> getBoardsByCategoryName(@RequestParam(defaultValue = "1", required = false, name = "page") int page,
                                                                @RequestParam(required = false) String query,
                                                                @RequestParam String categoryName){
        return new ApiPagingResponse<>(boardService.findBoardsByCategoryName(page,query,categoryName));
    }

    @GetMapping("/{boardId}/comments")
    public ApiPagingResponse<CommentResponse> getCommentsByBoardId(@RequestParam(required = false, defaultValue = "1") int page,
                                                                   @RequestParam(required = false) String query,
                                                                   @PathVariable Long boardId){
        return new ApiPagingResponse<>(commentService.findCommentsByBoardId(page,boardId));
    }

    @PostMapping("")
    public ApiResponse<String> createBoard(@RequestBody BoardCreate dto,
                                           @RequestParam(required = false) List<MultipartFile> images,
                                           @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse<>(boardService.createBoard(dto,images,principal.getUser().getId()));
    }

    @PutMapping("/{id}")
    public ApiResponse<String> updateBoard(@RequestBody BoardUpdate dto,
                                           @PathVariable Long id,
                                           @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse<>(boardService.updateBoard(dto,id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteBoard(@PathVariable Long id){
        return new ApiResponse<>(boardService.deleteBoard(id));
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
