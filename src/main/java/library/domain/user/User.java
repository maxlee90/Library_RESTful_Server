package library.domain.user;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends Person {
    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name ="firstname")
    private String firstName;

    @Column(name ="lastname")
    private  String lastName;

    @Column(name="address")
    private String address;

    @Column(name="type")
    private UserType type;


    public void setFirstName(String name){
        firstName = name;
    }

    public void setLastName(String name){
        lastName = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address"+ address + ", type="+ type;
    }

}
