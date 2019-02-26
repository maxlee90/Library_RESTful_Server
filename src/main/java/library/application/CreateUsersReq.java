package library.application;

import library.common.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateUsersReq extends BaseRequest {
    UserDto user;
}
