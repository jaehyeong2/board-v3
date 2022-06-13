package jjfactory.boardtest.business.repository.report;

import jjfactory.boardtest.business.domain.report.Warning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarningRepository extends JpaRepository<Warning,Long> {
}
