package library.infrastructure;

import java.util.List;
import library.domain.user.User;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
    extends CrudRepository<User, Long>, QuerydslPredicateExecutor<User>, UserRepositoryCustom {

  List<User> findAll();

  User findByFirstName(String first);
}
