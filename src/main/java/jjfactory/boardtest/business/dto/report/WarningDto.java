package jjfactory.boardtest.business.dto.report;

import jjfactory.boardtest.business.domain.report.WarningCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WarningDto {
    private WarningCode code;
}
