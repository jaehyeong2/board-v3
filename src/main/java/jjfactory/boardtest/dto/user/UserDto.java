package jjfactory.boardtest.dto.user;

import jjfactory.boardtest.domain.user.Gender;
import jjfactory.boardtest.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private String name;
    private String username;
    private String password;
    private String email;
    private Gender gender;
    private String phone;
    private List<String> roles;

    public UserDto(User user) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.roles = user.getRoles();
    }
}
