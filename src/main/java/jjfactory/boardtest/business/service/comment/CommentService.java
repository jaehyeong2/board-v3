package jjfactory.boardtest.business.service.comment;

import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.comment.Comment;
import jjfactory.boardtest.business.domain.comment.CommentLike;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.repository.comment.CommentQueryRepository;
import jjfactory.boardtest.global.dto.MyPageRequest;
import jjfactory.boardtest.global.dto.PagingResponse;
import jjfactory.boardtest.business.dto.comment.req.CommentChange;
import jjfactory.boardtest.business.dto.comment.req.CommentCreate;
import jjfactory.boardtest.business.dto.comment.res.CommentResponse;
import jjfactory.boardtest.global.handler.ex.BusinessException;
import jjfactory.boardtest.global.handler.ex.ErrorCode;
import jjfactory.boardtest.business.repository.board.BoardRepository;
import jjfactory.boardtest.business.repository.comment.CommentLikeRepository;
import jjfactory.boardtest.business.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final int LIKE_POINT = 1;
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;
    private final CommentLikeRepository likeRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public CommentResponse findComment(Long id){
        Comment comment = getComment(id);
        return new CommentResponse(comment);
    }

    @Transactional(readOnly = true)
    public PagingResponse<CommentResponse> findCommentsByBoardId(int page,Long boardId){
        Pageable pageRequest = new MyPageRequest(page,10).of();
        Page<CommentResponse> comments = commentQueryRepository.findComments(pageRequest,boardId);
        return new PagingResponse<>(comments);
    }

    public String createComment(CommentCreate dto, User user){
        Board board = boardRepository.findById(dto.getBoardId()).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
        Comment comment = Comment.create(dto, user, board);
        commentRepository.save(comment);

        user.pointUp(1);
        return "Y";
    }

    public String deleteComment(Long id){
        Comment comment = getComment(id);
        comment.deleteComment();
        return "Y";
    }

    public String updateContent(CommentChange dto, Long commentId, User user){
        Comment comment = getComment(commentId);

        if(!comment.getUser().equals(user)){
            throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
        }

        comment.changeContent(dto.getContent());
        return "Y";
    }

    public String commentLike(User user,Long commentId){
        Comment comment = getComment(commentId);
        comment.getUser().getId();
        CommentLike like = CommentLike.createLike(user, comment);
        User commentWriter = comment.getUser();

        // getId로 하면 if문 걸리는데, getUser로 하면 안걸림. 영속성컨텍스트 때문인가?
        if(commentWriter.getId().equals(user.getId())){
            throw new BusinessException(ErrorCode.COMMENT_HANDLE_DENIED);
        }else {
            likeRepository.save(like);
            commentWriter.pointUp(LIKE_POINT);
            comment.addLikeCount();
        }
        return "Y";
    }

    public String commentDislike(User user,Long commentId){
        Comment comment = getComment(commentId);
        CommentLike dislike = CommentLike.createDislike(user, comment);

        User commentWriter = comment.getUser();

        if(commentWriter.getId().equals(user.getId())){
            throw new BusinessException(ErrorCode.COMMENT_HANDLE_DENIED);
        }else {
            likeRepository.save(dislike);
            commentWriter.pointDown(LIKE_POINT);
            comment.subtractLikeCount();
        }

        return "Y";
    }

    private Comment getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
            throw new NoSuchElementException("조회_실패");
        });
        return comment;
    }

}
