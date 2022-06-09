package jjfactory.boardtest.business.controller.board;

import jjfactory.boardtest.global.dto.ApiResponse;
import jjfactory.boardtest.business.dto.board.CategoryDto;
import jjfactory.boardtest.business.dto.board.CategoryNameChange;
import jjfactory.boardtest.business.service.board.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/category")
@RequiredArgsConstructor
@RestController
public class CategoryApi {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ApiResponse<CategoryDto> getCategory(@PathVariable Long id){
        return new ApiResponse<>(categoryService.findCategoryById(id));
    }


    @PostMapping("")
    public ApiResponse<String> createCategory(@RequestBody CategoryDto dto){
        return new ApiResponse<>(categoryService.createCategory(dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteCategory(@PathVariable Long id){
        return new ApiResponse<>(categoryService.deleteCategory(id));
    }

    @PatchMapping("")
    public ApiResponse<String> deleteCategory(@RequestBody CategoryNameChange dto){
        return new ApiResponse<>(categoryService.changeTitle(dto));
    }
}
