package library.application;

import java.util.List;
import library.common.BookDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateBooksReq extends BaseRequest {

    List<BookDto> books;
}
