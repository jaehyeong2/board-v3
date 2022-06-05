package jjfactory.boardtest.service.comment;

import jjfactory.boardtest.domain.board.Board;
import jjfactory.boardtest.domain.comment.Comment;
import jjfactory.boardtest.domain.user.User;
import jjfactory.boardtest.dto.comment.CommentChangeDto;
import jjfactory.boardtest.dto.comment.CommentDto;
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

    public Comment findComment(Long id){
        return commentRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
    }

    public String createComment(CommentDto dto, User user){
        Board board = boardRepository.findById(dto.getBoardId()).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
        Comment.create(dto,user,board);
        return "Y";
    }

    public String deleteComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
        comment.deleteComment();
        return "Y";
    }

    public String updateContent(CommentChangeDto dto){
        Comment comment = getComment(dto);
        comment.changeContent(dto.getContent());
        return "Y";
    }

    private Comment getComment(CommentChangeDto dto) {
        Comment comment = commentRepository.findById(dto.getCommentId()).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
        return comment;
    }
}
