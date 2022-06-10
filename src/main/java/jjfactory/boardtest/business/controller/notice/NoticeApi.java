package jjfactory.boardtest.business.controller.notice;

import jjfactory.boardtest.business.dto.notice.NoticeContentChangeReq;
import jjfactory.boardtest.business.dto.notice.NoticeDto;
import jjfactory.boardtest.business.dto.notice.NoticeTitleChangeReq;
import jjfactory.boardtest.business.service.notice.NoticeService;
import jjfactory.boardtest.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/notices")
@RequiredArgsConstructor
@RestController
public class NoticeApi {
    private final NoticeService noticeService;

    @GetMapping("/{id}")
    public ApiResponse<NoticeDto> findNotice(@PathVariable Long id){
        return new ApiResponse<>(noticeService.findNoticeById(id));
    }

    @PostMapping("/")
    public ApiResponse<String> createNotice(@RequestBody NoticeDto dto){
        return new ApiResponse<>(noticeService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteNotice(@PathVariable Long id){
        return new ApiResponse<>(noticeService.delete(id));
    }

    @PatchMapping("/{id}/title")
    public ApiResponse<String> updateNoticeTitle(@PathVariable Long id, @RequestBody NoticeTitleChangeReq dto){
        return new ApiResponse<>(noticeService.changeTitle(id,dto));
    }

    @PatchMapping("/{id}/content")
    public ApiResponse<String> updateNoticeContent(@PathVariable Long id, @RequestBody NoticeContentChangeReq dto){
        return new ApiResponse<>(noticeService.changeContent(id,dto));
    }

}
