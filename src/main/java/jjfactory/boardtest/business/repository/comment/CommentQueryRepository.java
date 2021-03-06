package jjfactory.boardtest.business.repository.comment;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.boardtest.business.domain.comment.DeleteStatus;
import jjfactory.boardtest.business.dto.comment.res.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.boardtest.business.domain.comment.QComment.comment;

@RequiredArgsConstructor
@Repository
public class CommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<CommentResponse> findComments(Pageable pageable,Long boardId){
        List<CommentResponse> results = queryFactory.select(Projections.constructor(CommentResponse.class,
                        comment.id.as("commentId"),
                        comment.user.username.as("username"),
                        comment.content.as("content"),
                        comment.likeCount.as("likeCount"),
                        comment.createDate.as("createDate")))
                .from(comment)
                .where(comment.board.id.eq(boardId),comment.isDeleted.eq(DeleteStatus.NON_DELETED))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        int total = queryFactory.select(Projections.constructor(CommentResponse.class,
                        comment.id.as("commentId"),
                        comment.user.username.as("username"),
                        comment.content.as("content"),
                        comment.likeCount.as("likeCount"),
                        comment.createDate.as("createDate")))
                .from(comment)
                .where(comment.board.id.eq(boardId)).fetch().size();

        return new PageImpl<>(results,pageable, total);
    }

    // 대댓글 기능 구현
    public List<CommentResponse> findComments(Long boardId){
        return queryFactory.select(Projections.constructor(CommentResponse.class,comment))
                .from(comment)
                .leftJoin(comment.parent)
                .fetchJoin()
                .where(comment.board.id.eq(boardId)) // nulls first => 부모 없는게 최상위
                .orderBy(comment.parent.id.asc().nullsFirst(), comment.createDate.asc())
                .fetch();
    }
}
