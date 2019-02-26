package library.infrastructure;

import java.util.List;
import library.domain.book.Book;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends
        CrudRepository<Book,Long>,
        QuerydslPredicateExecutor,
        BookRepositoryCustom {

    List<Book> findAll();

    Book findByName(String name);

    List<Book> findAllByAuthor(String author);

    Book findById(long id);
}
