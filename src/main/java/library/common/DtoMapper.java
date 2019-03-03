package library.common;

import java.util.ArrayList;
import java.util.List;
import library.domain.book.Book;
import library.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DtoMapper {

    public List<BookDto> mapToBookDtos(List<Book> books){

        if(books == null) return null;

        List<BookDto> list = new ArrayList<>();

        for(Book book : books){
            list.add(mapToDto(book));
        }

        return list;
    }

    public List<UserDto> mapToUserDtos(List<User> users){
        if(users == null) return null;

        List<UserDto> list = new ArrayList<>();

        for(User user : users){
            list.add(mapToDto(user));
        }

        return list;
    }

    public BookDto mapToDto(Book book){
        return new BookDto(book.getId(),book.getName(),book.getAuthor(), book.getCategory(),book.getStatus());
    }
    public UserDto mapToDto(User user){
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getType());
    }
}
