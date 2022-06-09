package jjfactory.boardtest.business.controller.comment;

import jjfactory.boardtest.global.config.auth.PrincipalDetails;
import jjfactory.boardtest.global.dto.ApiResponse;
import jjfactory.boardtest.business.dto.comment.CommentChangeDto;
import jjfactory.boardtest.business.dto.comment.CommentDto;
import jjfactory.boardtest.business.dto.comment.FindCommentRes;
import jjfactory.boardtest.business.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comments")
@RequiredArgsConstructor
@RestController
public class CommentApi {
    private final CommentService commentService;

    @GetMapping("/{id}")
    public ApiResponse<FindCommentRes> getComment(@PathVariable Long id){
        return new ApiResponse<>(commentService.findComment(id));
    }

    @PostMapping("")
    public ApiResponse<String> createComment(@RequestBody CommentDto dto, @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse(commentService.createComment(dto,principal.getUser()));
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<String> deleteComment(@PathVariable Long commentId){
        return new ApiResponse(commentService.deleteComment(commentId));
    }

    @PatchMapping("/{commentId}")
    public ApiResponse<String> changeContent(@RequestBody CommentChangeDto dto,
                                             @PathVariable Long commentId,
                                             @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse(commentService.updateContent(dto,commentId,principal.getUser()));
    }

    @PostMapping("/{commentId}/like")
    public ApiResponse<String> likeBoard(@PathVariable Long commentId,
                                         @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse<>(commentService.commentLike(principal.getUser(),commentId));
    }

    @PostMapping("/{commentId}/dislike")
    public ApiResponse<String> dislikeBoard(@PathVariable Long commentId,
                                            @AuthenticationPrincipal PrincipalDetails principal){
        return new ApiResponse<>(commentService.commentDislike(principal.getUser(),commentId));
    }
}
