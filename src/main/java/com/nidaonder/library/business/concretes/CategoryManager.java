package com.nidaonder.library.business.concretes;

import com.nidaonder.library.business.abstracts.ICategoryService;
import com.nidaonder.library.core.exception.NotFoundException;
import com.nidaonder.library.core.utilities.Msg;
import com.nidaonder.library.dao.CategoryRepo;
import com.nidaonder.library.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(int id) {
        return this.categoryRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.categoryRepo.findAll(pageable);
    }

    @Override
    public Category update(Category category) {
        this.get(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public String delete(int id) {
        Category category = this.get(id);
        if (category.getBookList() != null && !category.getBookList().isEmpty()){
            return "The category could not be deleted because there are registered books associated with this category.";
        }
        this.categoryRepo.delete(category);
        return "Category deleted.";
    }
}
