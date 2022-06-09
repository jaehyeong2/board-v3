package jjfactory.boardtest.business.repository.report;

import jjfactory.boardtest.business.domain.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
