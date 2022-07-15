package jjfactory.boardtest.business.dto.user.res;

import jjfactory.boardtest.business.domain.user.User;
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
