package jjfactory.boardtest.domain.user;

import jjfactory.boardtest.domain.BaseTimeEntity;
import jjfactory.boardtest.dto.user.UserDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String name;
    @Column(length = 30)
    private String username;

    private String password;

    @Column(length = 20)
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Column(length = 30)
    private String email;

    private int warningCount;

    private Boolean activeState;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    protected User() {
    }
    @Builder
    public User(String name, String username, String password, String phone, String email, Boolean activeState, Gender gender,int warningCount,List<String> roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.activeState = activeState;
        this.roles = roles;
        this.gender = gender;
        this.warningCount = warningCount;
    }

    public static User createUser(UserDto dto, String password){
        return builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .password(password)
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .warningCount(0)
                .roles(Collections.singletonList("ROLE_USER"))
                .gender(dto.getGender())
                .activeState(true)
                .build();
    }

    public void withDraw(){
        this.activeState = false;
    }
}
