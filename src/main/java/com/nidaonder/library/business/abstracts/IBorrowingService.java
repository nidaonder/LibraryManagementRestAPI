package com.nidaonder.library.business.abstracts;

import com.nidaonder.library.entities.BookBorrowing;
import org.springframework.data.domain.Page;

public interface IBorrowingService {
    BookBorrowing save (BookBorrowing bookBorrowing);
    BookBorrowing get (int id);
    Page<BookBorrowing> cursor(int page, int pageSize);
    BookBorrowing update(BookBorrowing bookBorrowing);
    boolean delete(int id);
}
