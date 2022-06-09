package jjfactory.boardtest.business.domain.board;

import jjfactory.boardtest.business.domain.BaseTimeEntity;
import jjfactory.boardtest.business.dto.board.CategoryDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private Boolean isView;

    @Builder
    public Category(String name, Boolean isView) {
        this.name = name;
        this.isView = isView;
    }

    public static Category create(CategoryDto dto) {
        return builder()
                .name(dto.getName())
                .isView(true)
                .build();
    }

    public void disabled() {
        isView = false;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
