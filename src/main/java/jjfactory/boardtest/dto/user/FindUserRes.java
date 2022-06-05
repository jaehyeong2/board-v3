package jjfactory.boardtest.dto.user;

import jjfactory.boardtest.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindUserRes {
    private String username;
    private String email;
    private String phone;

    public FindUserRes(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }
}
