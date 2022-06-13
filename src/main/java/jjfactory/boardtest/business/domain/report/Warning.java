package jjfactory.boardtest.business.domain.report;

import jjfactory.boardtest.business.domain.BaseTimeEntity;
import jjfactory.boardtest.business.domain.admin.Admin;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.report.WarningDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Warning extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private WarningCode code;

    @Builder
    public Warning(Admin admin, User user, WarningCode code) {
        this.admin = admin;
        this.user = user;
        this.code = code;
    }

    public static Warning create(WarningDto dto,Admin admin,User user){
        return builder()
                .admin(admin)
                .user(user)
                .code(dto.getCode())
                .build();
    }
}
