package jjfactory.boardtest.repository.report;

import jjfactory.boardtest.domain.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
