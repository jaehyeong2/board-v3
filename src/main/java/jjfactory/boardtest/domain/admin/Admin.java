package jjfactory.boardtest.domain.admin;

import jjfactory.boardtest.domain.BaseTimeEntity;
import jjfactory.boardtest.domain.user.Gender;
import jjfactory.boardtest.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Admin{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String username;
    private String password;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    public Admin(String name, String username, String password, String phone, Gender gender) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
    }
}
