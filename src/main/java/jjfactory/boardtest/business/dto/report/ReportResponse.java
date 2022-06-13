package jjfactory.boardtest.business.dto.report;

import jjfactory.boardtest.business.domain.report.Report;
import jjfactory.boardtest.business.domain.report.ReportReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReportResponse {
    private Long toUserId;
    private Long fromUserId;
    private ReportReason reason;

    public ReportResponse(Report report) {
        this.toUserId = report.getToUser().getId();
        this.fromUserId = report.getFromUser().getId();
        this.reason = report.getReason();
    }
}
