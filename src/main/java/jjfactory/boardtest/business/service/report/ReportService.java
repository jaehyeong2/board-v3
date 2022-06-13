package jjfactory.boardtest.business.service.report;

import jjfactory.boardtest.business.domain.report.Report;
import jjfactory.boardtest.business.domain.report.Warning;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.report.ReportChangeReq;
import jjfactory.boardtest.business.dto.report.ReportDto;
import jjfactory.boardtest.business.dto.report.ReportResponse;
import jjfactory.boardtest.business.repository.admin.AdminRepository;
import jjfactory.boardtest.business.repository.report.ReportRepository;
import jjfactory.boardtest.business.repository.user.UserRepository;
import jjfactory.boardtest.global.handler.ex.BusinessException;
import jjfactory.boardtest.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public ReportResponse findReport(Long id){
        Report report = getReport(id);
        return new ReportResponse(report);
    }

    public String create(ReportDto dto){
        User fromUser = getUser(dto.getFromUserId());
        User toUser = getUser(dto.getFromUserId());
        Report report = Report.create(dto, fromUser, toUser);
        reportRepository.save(report);
        return "Y";
    }

    private User getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.NOT_FOUND_USER);
        });
        return user;
    }

    public String delete(Long id){
        reportRepository.deleteById(id);
        return "Y";
    }

    public String update(Long id, ReportChangeReq dto){
        Report report = getReport(id);

        report.changeReportReason(dto.getReason());
        return "Y";
    }

    private Report getReport(Long id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        });
        return report;
    }
}
