package com.nidaonder.library.business.abstracts;

import com.nidaonder.library.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {

    Category save (Category category);
    Category get (int id);
    Page<Category> cursor(int page, int pageSize);
    Category update(Category category);
    String delete(int id);
}
