package library.application;

import java.util.List;
import library.common.BookDto;
import library.common.ErrorDto;
import lombok.Getter;

@Getter
public class ReadBooksRes extends BaseResponse {

  private List<BookDto> books = null;

  public ReadBooksRes(ErrorDto errorDto) {
    super(errorDto);
  }

  public ReadBooksRes(List<BookDto> books) {
    this.books = books;
  }
}
