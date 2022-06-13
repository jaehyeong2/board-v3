package jjfactory.boardtest.business.dto.report;

import jjfactory.boardtest.business.domain.report.ReportReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReportDto {
    private ReportReason reportReason;
    private Long fromUserId;
    private Long toUserId;
}
