package jjfactory.boardtest.dto.user;

import jjfactory.boardtest.domain.user.User;
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
