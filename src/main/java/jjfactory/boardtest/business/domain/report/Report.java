package jjfactory.boardtest.business.domain.report;

import jjfactory.boardtest.business.domain.BaseTimeEntity;
import jjfactory.boardtest.business.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Report extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @OneToOne(fetch = FetchType.LAZY)
    private User toUser;

    @Enumerated(EnumType.STRING)
    private ReportReason reason;

}
