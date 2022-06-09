package jjfactory.boardtest.business.domain.report;

import jjfactory.boardtest.business.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Warning extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;
}
