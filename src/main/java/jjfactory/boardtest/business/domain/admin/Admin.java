package jjfactory.boardtest.business.domain.admin;

import jjfactory.boardtest.business.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

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

    public Admin(String adminName, String adminPhone, String adminEmail) {
        this.adminName = adminName;
        this.adminPhone = adminPhone;
        this.adminEmail = adminEmail;
    }

}
