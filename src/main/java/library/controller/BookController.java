package library.controller;

import javax.annotation.Nullable;
import javax.validation.Valid;
import library.application.*;
import library.common.ErrorCode;
import library.common.ErrorDto;
import library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("bookController")
@RequestMapping("books")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookController extends BaseController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ReadBooksRes read(@PathVariable long id) { return bookService.getBook(id);}

    @GetMapping
    public ReadBooksRes read(@Nullable @RequestBody ReadBooksReq req){
        return bookService.getBook(req);
    }

    @PostMapping
    public CreateBooksRes create(@Valid @RequestBody CreateBooksReq req){
        return bookService.addBook(req);
    }

    @PutMapping("/{id}")
    public UpdateBooksRes update(
            @PathVariable long id,
            @Valid @RequestBody UpdateBooksReq req){

        if(req==null) return new UpdateBooksRes(
                req.getMethod(),
                new ErrorDto(ErrorCode.BAD_DATA_FORMAT, "Request Body is empty."));

        switch (req.getMethod()){
            case RENT:
                return bookService.rentBook("test",id);
            default:
                return new UpdateBooksRes(
                        null,
                        new ErrorDto(ErrorCode.BAD_DATA_FORMAT,null));
        }
    }

}
