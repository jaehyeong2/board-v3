package jjfactory.boardtest.repository.board;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.boardtest.domain.board.Board;
import jjfactory.boardtest.dto.board.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.boardtest.domain.board.QBoard.board;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<BoardResponse> findAll(Pageable pageable){
        List<BoardResponse> result = queryFactory.select(Projections.constructor(BoardResponse.class,board))
                .from(board)
                .orderBy(board.createDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(result,pageable, result.size());
    }
}
