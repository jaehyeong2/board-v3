package jjfactory.boardtest.business.dto.report;

import jjfactory.boardtest.business.domain.report.Warning;
import jjfactory.boardtest.business.domain.report.WarningCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WarningResponse {
    private Long userId;
    private Long adminId;
    private WarningCode code;

    public WarningResponse(Warning warning) {
        this.userId = userId;
        this.adminId = adminId;
        this.code = code;
    }
}
