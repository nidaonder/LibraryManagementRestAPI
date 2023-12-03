package com.nidaonder.library.api;

import com.nidaonder.library.business.abstracts.ICategoryService;
import com.nidaonder.library.core.config.modelMapper.IModelMapperService;
import com.nidaonder.library.core.result.ResultData;
import com.nidaonder.library.core.utilities.ResultHelper;
import com.nidaonder.library.dto.request.AuthorSaveRequest;
import com.nidaonder.library.dto.request.CategorySaveRequest;
import com.nidaonder.library.dto.response.AuthorResponse;
import com.nidaonder.library.dto.response.CategoryResponse;
import com.nidaonder.library.entities.Author;
import com.nidaonder.library.entities.Category;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;


    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest){
        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest, Category.class);
        this.categoryService.save(saveCategory);
        CategoryResponse categoryResponse = this.modelMapper.forResponse().map(saveCategory, CategoryResponse.class);
        return ResultHelper.created(categoryResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id){
        Category category = this.categoryService.get(id);
        CategoryResponse categoryResponse = this.modelMapper.forResponse().map(category, CategoryResponse.class);
        return ResultHelper.success(categoryResponse);
    }
}
