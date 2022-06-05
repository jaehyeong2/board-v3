package jjfactory.boardtest.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.boardtest.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

import static jjfactory.boardtest.domain.user.QUser.user;


@RequiredArgsConstructor
@Repository
public class UserQueryRepository {
    private final JPAQueryFactory queryFactory;

    public User findByUsername(String username){
        User result = queryFactory.selectFrom(user)
                .where(user.username.eq(username))
                .fetchOne();

        if(user == null) throw new NoSuchElementException("조회실패");

        return result;
    }
}