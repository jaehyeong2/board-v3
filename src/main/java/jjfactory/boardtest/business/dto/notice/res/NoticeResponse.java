package jjfactory.boardtest.business.dto.notice.res;

import jjfactory.boardtest.business.domain.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NoticeResponse {
    private String content;
    private String title;
    private Boolean isView;

    public NoticeResponse(Notice notice) {
        this.content = content;
        this.title = title;
        this.isView = isView;
    }
}
