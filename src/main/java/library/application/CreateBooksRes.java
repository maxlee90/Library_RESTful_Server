package library.application;

import java.util.List;
import library.common.BookDto;
import library.common.ErrorDto;
import lombok.Getter;

@Getter
public class CreateBooksRes extends BaseResponse {

    List<BookDto> books;

    public CreateBooksRes(ErrorDto errorDto){ super(errorDto); }

    public CreateBooksRes(List<BookDto> books){
        this.books = books;

    }
}
