package jjfactory.boardtest.business.repository.board;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.boardtest.business.domain.user.QUser;
import jjfactory.boardtest.business.dto.board.res.BoardResponse;
import jjfactory.boardtest.business.repository.board.model.BoardSearchModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static jjfactory.boardtest.business.domain.board.QBoard.board;
import static jjfactory.boardtest.business.domain.user.QUser.*;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<BoardResponse> findAllBoards(Pageable pageable, BoardSearchModel model){
        List<BoardResponse> results = queryFactory.select(Projections.constructor(BoardResponse.class,board, user))
                .from(board)
                .innerJoin(user).on(user.id.eq(board.user.id))
                .where(board.isView.eq(true),
                        containsContent(model.getContent()),
                        containsTitle(model.getTitle()),
                        containsUsername(model.getUsername()))
                .orderBy(board.createDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        int total = queryFactory.select(Projections.constructor(BoardResponse.class, board,user))
                .from(board)
                .innerJoin(user).on(user.id.eq(board.user.id))
                .where(board.isView.eq(true),
                        containsContent(model.getContent()),
                        containsTitle(model.getTitle()),
                        containsUsername(model.getUsername()))
                .fetch().size();

        return new PageImpl<>(results,pageable, total);
    }

    public Page<BoardResponse> findBoardsByCategoryName(Pageable pageable, String categoryName){
        List<BoardResponse> results = queryFactory.select(Projections.constructor(BoardResponse.class,board))
                .from(board)
                .where(board.category.name.eq(categoryName))
                .orderBy(board.createDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(results,pageable, results.size());
    }

    private BooleanExpression containsContent(String content){
        if(StringUtils.hasText(content)) {
            return board.content.contains(content);
        }
        return null;
    }

    private BooleanExpression containsUsername(String username){
        if(StringUtils.hasText(username)) {
            return board.user.username.contains(username);
        }
        return null;
    }

    private BooleanExpression containsTitle(String title){
        if(StringUtils.hasText(title)) {
            return board.title.contains(title);
        }
        return null;
    }
}
