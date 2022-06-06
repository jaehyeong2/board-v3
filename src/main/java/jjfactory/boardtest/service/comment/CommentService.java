package jjfactory.boardtest.service.comment;

import jjfactory.boardtest.domain.board.Board;
import jjfactory.boardtest.domain.comment.Comment;
import jjfactory.boardtest.domain.user.User;
import jjfactory.boardtest.dto.comment.CommentChangeDto;
import jjfactory.boardtest.dto.comment.CommentDto;
import jjfactory.boardtest.dto.comment.FindCommentRes;
import jjfactory.boardtest.handler.ex.BusinessException;
import jjfactory.boardtest.handler.ex.ErrorCode;
import jjfactory.boardtest.repository.board.BoardRepository;
import jjfactory.boardtest.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public FindCommentRes findComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
        return new FindCommentRes(comment);
    }

    public String createComment(CommentDto dto, User user){
        Board board = boardRepository.findById(dto.getBoardId()).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
        Comment comment = Comment.create(dto, user, board);
        commentRepository.save(comment);

        user.pointUp(1);
        return "Y";
    }

    public String deleteComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
        comment.deleteComment();
        return "Y";
    }

    public String updateContent(CommentChangeDto dto,Long commentId,User user){
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        if(comment.getUser().equals(user)){
            throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
        }

        comment.changeContent(dto.getContent());
        return "Y";
    }

}
