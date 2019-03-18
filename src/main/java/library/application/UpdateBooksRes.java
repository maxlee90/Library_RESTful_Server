package library.application;

import library.common.BookDto;
import library.common.ErrorDto;
import library.controller.method.BookMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateBooksRes extends BaseResponse {

  BookMethod method;
  BookDto book;

  public UpdateBooksRes(BookMethod method, ErrorDto errorDto) {
    super(errorDto);
    this.method = method;
    book = null;
  }
}
