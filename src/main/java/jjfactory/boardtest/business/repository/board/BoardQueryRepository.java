package jjfactory.boardtest.business.repository.board;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.boardtest.business.dto.board.res.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.boardtest.business.domain.board.QBoard.board;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<BoardResponse> findAllBoards(Pageable pageable){
        List<BoardResponse> results = queryFactory.select(Projections.constructor(BoardResponse.class,board))
                .from(board)
                .where(board.isView.eq(true))
                .orderBy(board.createDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        int total = queryFactory.select(Projections.constructor(BoardResponse.class, board))
                .from(board)
                .where(board.isView.eq(true))
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
}
