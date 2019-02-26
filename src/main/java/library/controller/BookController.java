package library.controller;

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

    @GetMapping
    public ReadBooksRes read(){
        return bookService.getAllBooks();
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
                return bookService.rentBook("test",id,req);
            default:
                return new UpdateBooksRes(
                        null,
                        new ErrorDto(ErrorCode.BAD_DATA_FORMAT,null));
        }
    }

}
