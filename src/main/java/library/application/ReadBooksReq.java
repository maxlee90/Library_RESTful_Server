package library.application;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadBooksReq extends BaseRequest {
  String author;
}
