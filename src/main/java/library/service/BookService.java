package library.service;

import java.util.ArrayList;
import java.util.List;
import library.application.*;
import library.common.BookDto;
import library.common.DtoMapper;
import library.common.ErrorCode;
import library.common.ErrorDto;
import library.domain.book.Book;
import library.domain.book.RentResultStatus;
import library.domain.user.User;
import library.exception.ApplicationServiceException;
import library.infrastructure.BookRepository;
import library.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class BookService extends BaseService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;

    public ReadBooksRes getAllBooks(){
        return new ReadBooksRes(dtoMapper.mapToBookDtos(bookRepository.findAll()));
    }


    public CreateBooksRes addBook(CreateBooksReq req){

        if(req == null) return new CreateBooksRes(new ErrorDto(ErrorCode.BAD_DATA_FORMAT,null));

        List<BookDto> books = new ArrayList<>();
        req.getBooks().forEach(bookinfo -> {

            Book newBook = new Book();
            newBook.setName(bookinfo.getName());
            newBook.setAuthor(bookinfo.getAuthor());
            newBook.setCategory(bookinfo.getCategory());

            bookRepository.save(newBook);

            books.add(dtoMapper.mapToDto(newBook));

            log.info("Success to add book : {}", newBook.toString());

        });

        return new CreateBooksRes(books);
    }

    public UpdateBooksRes rentBook(String username, long id, UpdateBooksReq req) throws ApplicationServiceException {
        try{
            Book book = bookRepository.findById(id);
            User user = userRepository.findByFirstName(username);

            if(book == null) return new UpdateBooksRes(req.getMethod(), new ErrorDto(ErrorCode.NON_EXIST_BOOK,null));
            if(user == null) return new UpdateBooksRes(req.getMethod(), new ErrorDto(ErrorCode.NON_EXIST_USER,null));

            if (book.isRentable()) {
                log.info("book info => {}", book.toString());

                book.rent(user.getId());
                bookRepository.save(book);

                log.info("[{}] {}, {}", req.getMethod(), RentResultStatus.SUCCESS_RENT, book.toString());

                return new UpdateBooksRes(req.getMethod(), dtoMapper.mapToDto(book));
            }
            else{
                log.info("[{}] {}, {}, ", req.getMethod(), RentResultStatus.ALEADY_OCCUPIED, book.toString());
                return new UpdateBooksRes(req.getMethod(),
                        new ErrorDto(ErrorCode.UNAVAILABLE,RentResultStatus.ALEADY_OCCUPIED.toString()));
            }
        } catch (NullPointerException e){
            log.error("error message on rentBook() : ", e.toString());
            throw new ApplicationServiceException(new ErrorDto(ErrorCode.INTERNAL_SERVER_ERROR,null));
        }
    }
}
