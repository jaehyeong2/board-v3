package jjfactory.boardtest.repository.inquiry;

import jjfactory.boardtest.domain.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry,Long> {
}
