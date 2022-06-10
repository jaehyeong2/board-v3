package jjfactory.boardtest.business.repository.notice;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.boardtest.business.dto.notice.res.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.boardtest.business.domain.notice.QNotice.notice;

@RequiredArgsConstructor
@Repository
public class NoticeQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<NoticeResponse> findNotices(Pageable pageable){
        List<NoticeResponse> results = queryFactory.select(Projections.constructor(NoticeResponse.class, notice))
                .from(notice)
                .orderBy(notice.createDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(results,pageable,pageable.getPageSize());
    }
}
