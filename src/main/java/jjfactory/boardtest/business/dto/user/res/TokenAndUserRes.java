package jjfactory.boardtest.business.dto.user.res;

import jjfactory.boardtest.business.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenAndUserRes {
    private String username;
    private String token;

    public TokenAndUserRes(User user, String token) {
        this.username = user.getUsername();
        this.token = token;
    }
}
