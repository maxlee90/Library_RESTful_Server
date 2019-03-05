package library.common;

import java.io.Serializable;
import library.domain.book.BookStatus;
import library.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto implements Serializable {
  Long id;
  String name;
  String author;
  Category category;
  BookStatus bookStatus;
}
