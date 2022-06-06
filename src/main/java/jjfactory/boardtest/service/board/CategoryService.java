package jjfactory.boardtest.service.board;

import jjfactory.boardtest.domain.board.Category;
import jjfactory.boardtest.dto.board.CategoryDto;
import jjfactory.boardtest.dto.board.CategoryNameChange;
import jjfactory.boardtest.repository.board.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CategoryDto findCategoryById(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });
        return new CategoryDto(category);
    }

    public String createCategory(CategoryDto dto){
        Category category = Category.create(dto);
        categoryRepository.save(category);
        return "Y";
    }

    public String deleteCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        category.disabled();
        return "Y";
    }

    public String changeTitle(CategoryNameChange dto){
        Category category = categoryRepository.findById(dto.getId()).orElseThrow(() -> {
            throw new NoSuchElementException("조회 실패");
        });

        category.changeName(dto.getName());
        return "Y";
    }
}
