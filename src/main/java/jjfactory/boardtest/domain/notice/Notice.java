package jjfactory.boardtest.domain.notice;

import jjfactory.boardtest.domain.BaseTimeEntity;
import jjfactory.boardtest.dto.notice.NoticeDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Notice extends BaseTimeEntity{
    @Id @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "notice")
    private List<NoticeImage> imageList = new ArrayList<>();

    @Column(length = 100)
    private String title;
    private String content;

    @Builder
    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Notice create(NoticeDto dto){
        return builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public void addImage(NoticeImage image){
        this.imageList.add(image);
    }
}
