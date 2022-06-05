package jjfactory.boardtest.domain.notice;

import jjfactory.boardtest.domain.BaseTimeEntity;
import jjfactory.boardtest.domain.board.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class NoticeImage extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    private String imageUrl;
}
