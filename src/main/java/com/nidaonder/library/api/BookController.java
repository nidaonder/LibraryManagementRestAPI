package com.nidaonder.library.api;

import com.nidaonder.library.business.abstracts.IAuthorService;
import com.nidaonder.library.business.abstracts.IBookService;
import com.nidaonder.library.business.abstracts.IPublisherService;
import com.nidaonder.library.core.config.modelMapper.IModelMapperService;
import com.nidaonder.library.core.result.Result;
import com.nidaonder.library.core.result.ResultData;
import com.nidaonder.library.core.utilities.ResultHelper;
import com.nidaonder.library.dto.request.BookSaveRequest;
import com.nidaonder.library.dto.request.BookUpdateRequest;
import com.nidaonder.library.dto.response.AuthorResponse;
import com.nidaonder.library.dto.response.BookResponse;
import com.nidaonder.library.dto.response.CursorResponse;
import com.nidaonder.library.dto.response.PublisherResponse;
import com.nidaonder.library.entities.Author;
import com.nidaonder.library.entities.Book;
import com.nidaonder.library.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final IBookService bookService;
    private final IModelMapperService modelMapper;
    private final IAuthorService authorService;
    private final IPublisherService publisherService;

    public BookController(
            IBookService bookService,
            IModelMapperService modelMapper,
            IAuthorService authorService,
            IPublisherService publisherService
    ){
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest){
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);

        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        saveBook.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookSaveRequest.getPublisherId());
        saveBook.setPublisher(publisher);

        this.bookService.save(saveBook);
        BookResponse bookResponse = this.modelMapper.forResponse().map(saveBook, BookResponse.class);
        return ResultHelper.created(bookResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id){
        Book book = this.bookService.get(id);
        BookResponse bookResponse = this.modelMapper.forResponse().map(book, BookResponse.class);
        return ResultHelper.success(bookResponse);
    }

    @GetMapping("/{id}/author")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> getAuthor(@PathVariable("id") int id){
        Book book = this.bookService.get(id);
        AuthorResponse authorResponse = this.modelMapper.forResponse().map(book.getAuthor(), AuthorResponse.class);
        return ResultHelper.success(authorResponse);
    }

    @GetMapping("/{id}/publisher")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> getPublisher(@PathVariable("id") int id){
        Book book = this.bookService.get(id);
        PublisherResponse publisherResponse = this.modelMapper.forResponse().map(book.getPublisher(), PublisherResponse.class);
        return ResultHelper.success(publisherResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        Page<Book> bookPage = this.bookService.cursor(page, pageSize);
        Page<BookResponse> bookResponsePage = bookPage
                .map(book -> this.modelMapper.forResponse().map(book, BookResponse.class));
        return ResultHelper.cursor(bookResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.bookService.delete(id);
        return ResultHelper.ok();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest){
        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);

        Author author = this.authorService.get(bookUpdateRequest.getAuthorId());
        updateBook.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookUpdateRequest.getPublisherId());
        updateBook.setPublisher(publisher);

        this.bookService.update(updateBook);
        BookResponse bookResponse = this.modelMapper.forResponse().map(updateBook, BookResponse.class);
        return ResultHelper.success(bookResponse);
    }
}
