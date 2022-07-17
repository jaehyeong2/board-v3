package jjfactory.boardtest.business.dto.user.res;

import jjfactory.boardtest.business.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class UserInfoRes {
    private Long userId;
    private String name;
    private String username;
    private String email;
    private String phone;
    private int activePoint;
    private int warningCount;
    private LocalDateTime createDate;

    @Builder
    public UserInfoRes(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.activePoint = user.getActivePoint();
        this.warningCount = user.getWarningCount();
        this.createDate = user.getCreateDate();
    }

    @Builder
    public UserInfoRes(Long userId, String name, String username, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
}
