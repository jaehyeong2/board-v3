package jjfactory.boardtest.business.controller.notice;

import jjfactory.boardtest.business.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
public class NoticeApi {
    private final NoticeService noticeService;
}
