package library.domain.book;

import javax.persistence.*;
import library.domain.BaseEntity;
import library.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "books")
public class Book extends BaseEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name ="category")
    private Category category;

    @Column(name = "status")
    private BookStatus status = BookStatus.RENTABLE;

    @Column(name = "user_id")
    private Long user_id;

    public boolean isRentable(){
        return status == BookStatus.RENTABLE;
    }

    public boolean isReturnable() { return status == BookStatus.UNRENTABLE;}

    // 책 빌리기
    public boolean rent(long user_id){
        if(!isRentable()) return false;

        this.user_id = user_id;
        status = BookStatus.UNRENTABLE;

        return true;
    }

    // 책 반납하기
    public boolean _return(long user_id){
        if(this.user_id != user_id) return false;

        this.user_id = null;
        status = BookStatus.RENTABLE;

        return true;
    }

    public void setName(String name){
        this.name = name;
    }

    public  void setAuthor(String author){
        this.author= author;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    @Override
    public String toString(){
        return "[Book Info]" +
                "SerialNumber : " + id + ", " +
                "Name : " + name + ", " +
                "Author : " + author + ", " +
                "Category : " + category + ", " +
                "Rent Status : " + status;
    }
}
