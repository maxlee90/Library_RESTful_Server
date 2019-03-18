package library.infrastructure;

import library.domain.book.Book;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl extends QuerydslRepositorySupport implements BookRepositoryCustom {

  public BookRepositoryImpl() {

    super(Book.class);
  }
}
