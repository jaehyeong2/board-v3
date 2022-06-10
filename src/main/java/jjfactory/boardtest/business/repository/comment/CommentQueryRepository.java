package jjfactory.boardtest.business.repository.comment;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.boardtest.business.dto.comment.CommentResponse;
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
        List<CommentResponse> results = queryFactory.select(Projections.constructor(CommentResponse.class, comment))
                .from(comment)
                .where(comment.board.id.eq(boardId))
                .orderBy(comment.createDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(results,pageable, results.size());
    }
}
