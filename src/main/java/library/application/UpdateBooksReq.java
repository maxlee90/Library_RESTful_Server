package library.application;

import library.common.BookDto;
import library.controller.method.BookMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBooksReq {
  BookMethod method;
  BookDto book;
}
