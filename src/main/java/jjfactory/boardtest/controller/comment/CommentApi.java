package jjfactory.boardtest.controller.comment;

import jjfactory.boardtest.domain.user.User;
import jjfactory.boardtest.dto.comment.CommentChangeDto;
import jjfactory.boardtest.dto.comment.CommentDto;
import jjfactory.boardtest.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comments")
@RequiredArgsConstructor
@RestController
public class CommentApi {
    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<String> createComment(@RequestBody CommentDto dto, @AuthenticationPrincipal User user){
        return new ResponseEntity(commentService.createComment(dto,user), HttpStatus.OK);
    }


    @DeleteMapping("")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId){
        return new ResponseEntity(commentService.deleteComment(commentId), HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<String> changeContent(@RequestBody CommentChangeDto dto){
        return new ResponseEntity(commentService.updateContent(dto), HttpStatus.OK);
    }
}
