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

    public void rent(long user_id){
        this.user_id = user_id;
        status = BookStatus.UNRENTABLE;
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
        return id + ":" + name + ":" + author + ":" + category +":"+ status + ":" + user_id;
    }
}
