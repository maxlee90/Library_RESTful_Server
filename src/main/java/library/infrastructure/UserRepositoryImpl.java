package library.infrastructure;

import library.domain.user.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public class UserRepositoryImpl extends QuerydslRepositorySupport
    implements UserRepositoryCustom {

    public UserRepositoryImpl(){
        super(User.class);
    }

}
