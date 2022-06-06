package jjfactory.boardtest.controller.board;

import jjfactory.boardtest.dto.ApiResponse;
import jjfactory.boardtest.dto.board.CategoryDto;
import jjfactory.boardtest.dto.board.CategoryNameChange;
import jjfactory.boardtest.service.board.CategoryService;
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
