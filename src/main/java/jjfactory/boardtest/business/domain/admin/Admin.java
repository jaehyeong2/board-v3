package jjfactory.boardtest.business.domain.admin;

import jjfactory.boardtest.business.domain.user.Gender;
import jjfactory.boardtest.business.domain.user.Role;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.admin.AdminCreateDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Admin extends User{

    @Comment("관리자명")
    private String adminName;

    @Comment("관리자 전화번호")
    private String adminPhone;

    @Comment("관리자 이메일")
    private String adminEmail;

    @Builder(builderMethodName = "AdminBuilder")
    public Admin(String name, String username, String password, String phone, List<Role> roles, String email, int warningCount, int activePoint, Boolean activeState, Gender gender, String adminName, String adminPhone, String adminEmail) {
        super(name, username, password, phone, roles, email, warningCount, activePoint, activeState, gender);
        this.adminName = adminName;
        this.adminPhone = adminPhone;
        this.adminEmail = adminEmail;
    }

    public static Admin enrollAdmin(AdminCreateDto dto){
        return AdminBuilder()
                .roles(Collections.singletonList(Role.ROLE_ADMIN))
                .adminPhone(dto.getAdminPhone())
                .adminEmail(dto.getAdminEmail())
                .adminName(dto.getAdminName())
                .build();
    }
}
