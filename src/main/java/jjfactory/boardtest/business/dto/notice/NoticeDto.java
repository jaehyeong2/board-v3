package jjfactory.boardtest.business.dto.notice;

import jjfactory.boardtest.business.domain.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NoticeDto {
    private String title;
    private String content;

    public NoticeDto(Notice notice) {
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }
}
