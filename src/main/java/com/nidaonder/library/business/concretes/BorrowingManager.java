package com.nidaonder.library.business.concretes;

import com.nidaonder.library.business.abstracts.IBorrowingService;
import com.nidaonder.library.core.exception.NotFoundException;
import com.nidaonder.library.core.exception.StockErrorException;
import com.nidaonder.library.core.utilities.Msg;
import com.nidaonder.library.dao.BorrowingRepo;
import com.nidaonder.library.entities.BookBorrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BorrowingManager implements IBorrowingService {

    private final BorrowingRepo borrowingRepo;

    public BorrowingManager(BorrowingRepo borrowingRepo) {
        this.borrowingRepo = borrowingRepo;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        int stock = bookBorrowing.getBook().getStock();
        if (stock > 0){
            bookBorrowing.getBook().setStock(stock - 1);
            return this.borrowingRepo.save(bookBorrowing);
        } else {
            throw new StockErrorException(Msg.INSUFFICIENT_STOCK);
        }
    }

    @Override
    public BookBorrowing get(int id) {
        return this.borrowingRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.borrowingRepo.findAll(pageable);
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        this.get(bookBorrowing.getId());
        return this.borrowingRepo.save(bookBorrowing);
    }

    @Override
    public boolean delete(int id) {
        BookBorrowing bookBorrowing = this.get(id);
        this.borrowingRepo.delete(bookBorrowing);
        return true;
    }
}
