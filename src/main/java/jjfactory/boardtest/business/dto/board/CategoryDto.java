package jjfactory.boardtest.business.dto.board;

import jjfactory.boardtest.business.domain.board.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryDto {
    private String name;

    public CategoryDto(Category category) {
        this.name = category.getName();
    }
}
