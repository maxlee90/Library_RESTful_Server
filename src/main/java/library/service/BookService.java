package library.service;

import java.util.ArrayList;
import java.util.List;
import library.application.*;
import library.common.BookDto;
import library.common.DtoMapper;
import library.common.ErrorCode;
import library.common.ErrorDto;
import library.controller.method.BookMethod;
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

    public ReadBooksRes getBook(long id){
        List<BookDto> books = new ArrayList<>() ;

        Book book = bookRepository.findById(id);
        if(book == null) return new ReadBooksRes(new ErrorDto(ErrorCode.NON_EXIST_BOOK,null));

        books.add(dtoMapper.mapToDto(book));

        return new ReadBooksRes(books);
    }

    public ReadBooksRes getBook(ReadBooksReq req){

        if(req == null) return getAllBooks();
        if(req.getAuthor() == null) return getAllBooks();

        List<Book> books = bookRepository.findAllByAuthor(req.getAuthor());
        if(books == null) return new ReadBooksRes(new ErrorDto(ErrorCode.NON_EXIST_BOOK,null));

        return new ReadBooksRes(dtoMapper.mapToBookDtos(books));
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

    public UpdateBooksRes rentBook(String username, long id) throws ApplicationServiceException {
        try{
            Book book = bookRepository.findById(id);
            User user = userRepository.findByFirstName(username);

            if(book == null) return new UpdateBooksRes(BookMethod.RENT, new ErrorDto(ErrorCode.NON_EXIST_BOOK,null));
            if(user == null) return new UpdateBooksRes(BookMethod.RENT, new ErrorDto(ErrorCode.NON_EXIST_USER,null));

            if (!book.rent(user.getId())) {
                log.info("[{}] {}, {}, ", BookMethod.RENT, RentResultStatus.ALEADY_OCCUPIED, book.toString());
                return new UpdateBooksRes(BookMethod.RENT,
                        new ErrorDto(ErrorCode.INVALID,RentResultStatus.ALEADY_OCCUPIED.toString()));
            }

            bookRepository.save(book);

            log.info("[{}] {}, {}", BookMethod.RENT, RentResultStatus.SUCCESS_RENT, book.toString());
            return new UpdateBooksRes(BookMethod.RENT, dtoMapper.mapToDto(book));

        } catch (NullPointerException e){
            log.error("error message on rentBook() : ", e.toString());
            throw new ApplicationServiceException(new ErrorDto(ErrorCode.INTERNAL_SERVER_ERROR,null));
        }
    }

    public UpdateBooksRes returnBook(String username, long id) throws ApplicationServiceException {
        try{
            Book book = bookRepository.findById(id);
            User user = userRepository.findByFirstName(username);

            if(book == null) return new UpdateBooksRes(BookMethod.RETURN, new ErrorDto(ErrorCode.NON_EXIST_BOOK,null));
            if(user == null) return new UpdateBooksRes(BookMethod.RETURN, new ErrorDto(ErrorCode.NON_EXIST_USER,null));

            if (!book.isReturnable()) {
                log.info("[{}] {}, {}, ", BookMethod.RETURN, RentResultStatus.NO_ONE_RENT, book.toString());
                return new UpdateBooksRes(BookMethod.RETURN,
                        new ErrorDto(ErrorCode.INVALID,RentResultStatus.NO_ONE_RENT.toString()));
            }

            if(!book._return(id)) {
                log.info("[{}] {}, {}, ", BookMethod.RETURN, RentResultStatus.UNAUTHORIZED, book.toString());
                return new UpdateBooksRes(BookMethod.RETURN,
                        new ErrorDto(ErrorCode.INVALID,RentResultStatus.UNAUTHORIZED.toString()));
            }

            bookRepository.save(book);

            log.info("[{}] {}, {}", BookMethod.RETURN, RentResultStatus.SUCCESS_RETURN, book.toString());
            return new UpdateBooksRes(BookMethod.RETURN, dtoMapper.mapToDto(book));

        } catch (NullPointerException e){
            log.error("error message on rentBook() : ", e.toString());
            throw new ApplicationServiceException(new ErrorDto(ErrorCode.INTERNAL_SERVER_ERROR,null));
        }
    }
}
