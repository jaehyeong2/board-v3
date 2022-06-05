package jjfactory.boardtest.domain.user;

import jjfactory.boardtest.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String username;
    private String password;
    private String phone;
    private Gender gender;

    @Builder
    public User(String name, String username, String password, String phone, Gender gender) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
    }
}
