package jjfactory.boardtest.business.domain.user;

import jjfactory.boardtest.business.domain.BaseTimeEntity;
import jjfactory.boardtest.business.dto.user.UserDto;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String name;
    @Comment(value = "아이디")
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

    @Comment("누적 경고 횟수")
    private int warningCount;
    
    @Comment("활동 점수")
    private int activePoint;


    @Comment("활동상태 : 활동 / 휴면")
    private Boolean activeState;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    protected User() {
    }
    @Builder
    public User(String name, String username, String password, String phone, String email, Boolean activeState, Gender gender,int warningCount,List<String> roles,int activePoint) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.activeState = activeState;
        this.roles = roles;
        this.gender = gender;
        this.warningCount = warningCount;
        this.activePoint = activePoint;
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
                .activePoint(0)
                .build();
    }

    public void withDraw(){
        this.activeState = false;
    }

    public void pointUp(int activePoint) {
        this.activePoint += activePoint;
    }

    public void pointDown(int activePoint) {
        this.activePoint -= activePoint;
    }
}
