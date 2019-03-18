package library.common;

import java.io.Serializable;
import library.domain.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {
  Long id;
  String firstName;
  String lastName;
  String address;
  UserType type;
}
