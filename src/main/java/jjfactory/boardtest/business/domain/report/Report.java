package jjfactory.boardtest.business.domain.report;

import jjfactory.boardtest.business.domain.BaseTimeEntity;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.report.ReportDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Report extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Comment("신고접수 유저")
    @OneToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @Comment("신고당한 유저")
    @OneToOne(fetch = FetchType.LAZY)
    private User toUser;

    @Enumerated(EnumType.STRING)
    private ReportReason reason;
    
    @Enumerated(EnumType.STRING)
    private AnswerState state;

    @Builder
    public Report(User fromUser, User toUser, ReportReason reason) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.reason = reason;
    }

    public static Report create(ReportDto dto,User fromUser,User toUser){
        return builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .reason(dto.getReportReason())
                .build();
    }
}
