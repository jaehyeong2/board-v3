package jjfactory.boardtest.controller.notice;

import jjfactory.boardtest.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
public class NoticeApi {
    private final NoticeService noticeService;
}
