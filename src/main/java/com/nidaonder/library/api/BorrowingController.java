package com.nidaonder.library.api;

import com.nidaonder.library.business.abstracts.IBookService;
import com.nidaonder.library.business.abstracts.IBorrowingService;
import com.nidaonder.library.core.config.modelMapper.IModelMapperService;
import com.nidaonder.library.core.result.Result;
import com.nidaonder.library.core.result.ResultData;
import com.nidaonder.library.core.utilities.ResultHelper;
import com.nidaonder.library.dto.request.BorrowingSaveRequest;
import com.nidaonder.library.dto.request.BorrowingUpdateRequest;
import com.nidaonder.library.dto.response.BookResponse;
import com.nidaonder.library.dto.response.BorrowingResponse;
import com.nidaonder.library.dto.response.CursorResponse;
import com.nidaonder.library.entities.Book;
import com.nidaonder.library.entities.BookBorrowing;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

        Book book = this.bookService.get(borrowingSaveRequest.getBorrowingBook());
        saveBorrowing.setBook(book);

        this.borrowingService.save(saveBorrowing);
        BorrowingResponse borrowingResponse = this.modelMapper.forResponse().map(saveBorrowing, BorrowingResponse.class);
        return ResultHelper.created(borrowingResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BorrowingResponse> get(@PathVariable("id") int id){
        BookBorrowing bookBorrowing = this.borrowingService.get(id);
        BorrowingResponse borrowingResponse = this.modelMapper.forResponse().map(bookBorrowing, BorrowingResponse.class);
        return ResultHelper.success(borrowingResponse);
    }

    @GetMapping("/{id}/book")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> getBook(@PathVariable("id") int id){
        BookBorrowing bookBorrowing = this.borrowingService.get(id);
        BookResponse bookResponse = this.modelMapper.forResponse().map(bookBorrowing.getBook(), BookResponse.class);
        return ResultHelper.success(bookResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BorrowingResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        Page<BookBorrowing> borrowingPage = this.borrowingService.cursor(page, pageSize);
        Page<BorrowingResponse> borrowingResponsePage = borrowingPage
                .map(bookBorrowing -> this.modelMapper.forResponse().map(bookBorrowing, BorrowingResponse.class));
        return ResultHelper.cursor(borrowingResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.borrowingService.delete(id);
        return ResultHelper.ok();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BorrowingResponse> update(@Valid @RequestBody BorrowingUpdateRequest borrowingUpdateRequest){
        BookBorrowing updateBorrowing = this.modelMapper.forRequest().map(borrowingUpdateRequest, BookBorrowing.class);

        Book book = this.bookService.get(borrowingUpdateRequest.getBorrowingBook());
        updateBorrowing.setBook(book);

        this.borrowingService.update(updateBorrowing);
        BorrowingResponse borrowingResponse = this.modelMapper.forResponse().map(updateBorrowing, BorrowingResponse.class);
        return ResultHelper.success(borrowingResponse);
    }
}
