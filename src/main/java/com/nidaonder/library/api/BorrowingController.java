package com.nidaonder.library.api;

import com.nidaonder.library.business.abstracts.IBookService;
import com.nidaonder.library.business.abstracts.IBorrowingService;
import com.nidaonder.library.core.config.modelMapper.IModelMapperService;
import com.nidaonder.library.core.result.ResultData;
import com.nidaonder.library.core.utilities.ResultHelper;
import com.nidaonder.library.dto.request.BookSaveRequest;
import com.nidaonder.library.dto.request.BorrowingSaveRequest;
import com.nidaonder.library.dto.response.BookResponse;
import com.nidaonder.library.dto.response.BorrowingResponse;
import com.nidaonder.library.entities.Author;
import com.nidaonder.library.entities.Book;
import com.nidaonder.library.entities.BookBorrowing;
import com.nidaonder.library.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/book-borrowings")
public class BorrowingController {

    private final IBorrowingService borrowingService;
    private final IModelMapperService modelMapper;
    private final IBookService bookService;


    public BorrowingController(
            IBorrowingService borrowingService,
            IModelMapperService modelMapper,
            IBookService bookService) {
        this.borrowingService = borrowingService;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BorrowingResponse> save(@Valid @RequestBody BorrowingSaveRequest borrowingSaveRequest){
        BookBorrowing saveBorrowing = this.modelMapper.forRequest().map(borrowingSaveRequest, BookBorrowing.class);

        Book book = this.bookService.get(borrowingSaveRequest.getBorrowingBookId());
        saveBorrowing.setBook(book);

        this.borrowingService.save(saveBorrowing);
        BorrowingResponse borrowingResponse = this.modelMapper.forResponse().map(saveBorrowing, BorrowingResponse.class);
        return ResultHelper.created(borrowingResponse);
    }
}
