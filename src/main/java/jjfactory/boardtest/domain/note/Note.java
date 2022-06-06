package jjfactory.boardtest.domain.note;

import jjfactory.boardtest.domain.BaseTimeEntity;
import jjfactory.boardtest.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Note extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User sender;

    @OneToOne(fetch = FetchType.LAZY)
    private User receiver;

    private String title;
    private String content;

    @Builder
    public Note(User sender, User receiver, String title, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.content = content;
    }
}
