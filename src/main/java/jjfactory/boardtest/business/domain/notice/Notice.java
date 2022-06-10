package jjfactory.boardtest.business.domain.notice;

import jjfactory.boardtest.business.domain.BaseTimeEntity;
import jjfactory.boardtest.business.dto.notice.NoticeDto;
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
public class Notice extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "notice")
    private List<NoticeImage> imageList = new ArrayList<>();

    @Column(length = 100)
    private String title;
    private String content;

    private Boolean isView;

    @Builder
    public Notice(String title, String content,Boolean isView) {
        this.title = title;
        this.content = content;
        this.isView = isView;
    }

    public static Notice create(NoticeDto dto){
        return builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .isView(true)
                .build();
    }

    public void addImage(NoticeImage image){
        this.imageList.add(image);
    }

    public void delete() {
        isView = false;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
