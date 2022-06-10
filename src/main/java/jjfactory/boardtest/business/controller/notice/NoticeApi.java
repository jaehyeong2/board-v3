package jjfactory.boardtest.business.controller.notice;

import jjfactory.boardtest.business.dto.notice.req.NoticeContentChangeReq;
import jjfactory.boardtest.business.dto.notice.req.NoticeDto;
import jjfactory.boardtest.business.dto.notice.req.NoticeTitleChangeReq;
import jjfactory.boardtest.business.dto.notice.res.NoticeResponse;
import jjfactory.boardtest.business.service.notice.NoticeService;
import jjfactory.boardtest.global.dto.ApiPagingResponse;
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

    @GetMapping("")
    public ApiPagingResponse<NoticeResponse> findNotices(@RequestParam(defaultValue = "1", required = false, name = "page") int page){
        return new ApiPagingResponse<>(noticeService.findNotice(page));
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
