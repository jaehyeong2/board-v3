package jjfactory.boardtest.business.service.notice;

import jjfactory.boardtest.business.domain.notice.Notice;
import jjfactory.boardtest.business.dto.notice.req.NoticeContentChangeReq;
import jjfactory.boardtest.business.dto.notice.req.NoticeDto;
import jjfactory.boardtest.business.dto.notice.req.NoticeTitleChangeReq;
import jjfactory.boardtest.business.dto.notice.res.NoticeResponse;
import jjfactory.boardtest.business.repository.notice.NoticeQueryRepository;
import jjfactory.boardtest.business.repository.notice.NoticeRepository;
import jjfactory.boardtest.global.dto.MyPageRequest;
import jjfactory.boardtest.global.dto.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final NoticeQueryRepository noticeQueryRepository;

    @Transactional(readOnly = true)
    public NoticeDto findNoticeById(Long id){
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        return new NoticeDto(notice);
    }

    @Transactional(readOnly = true)
    public PagingResponse<NoticeResponse> findNotice(int page){
        PageRequest pageRequest = new MyPageRequest(page, 10).of();
        Page<NoticeResponse> notices = noticeQueryRepository.findNotices(pageRequest);

        return new PagingResponse<>(notices);
    }

    public String create(NoticeDto dto){
        Notice notice = Notice.create(dto);
        noticeRepository.save(notice);
        return "Y";
    }

    public String delete(Long id){
        Notice notice = getNotice(id);
        notice.delete();
        return "Y";
    }

    public String changeTitle(Long id, NoticeTitleChangeReq dto){
        Notice notice = getNotice(id);
        notice.changeTitle(dto.getTitle());
        return "Y";
    }

    public String changeContent(Long id, NoticeContentChangeReq dto){
        Notice notice = getNotice(id);
        notice.changeContent(dto.getContent());
        return "Y";
    }

    private Notice getNotice(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
        return notice;
    }
}
