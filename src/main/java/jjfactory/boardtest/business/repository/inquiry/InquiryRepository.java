package jjfactory.boardtest.business.repository.inquiry;

import jjfactory.boardtest.business.domain.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry,Long> {
}
