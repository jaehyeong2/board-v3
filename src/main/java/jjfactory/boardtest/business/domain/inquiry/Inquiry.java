package jjfactory.boardtest.business.domain.inquiry;

import jjfactory.boardtest.business.domain.BaseTimeEntity;
import jjfactory.boardtest.business.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Inquiry extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String content;
    private String title;

    @Enumerated(EnumType.STRING)
    private InquiryState state;

    @Builder
    public Inquiry(User user, String content, String title,InquiryState state) {
        this.user = user;
        this.content = content;
        this.title = title;
        this.state = state;
    }
}
