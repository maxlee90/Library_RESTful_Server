package library.application;

import library.common.ErrorDto;
import library.common.UserDto;
import lombok.Getter;

@Getter
public class CreateUsersRes extends BaseResponse {
    UserDto user;

    public CreateUsersRes(ErrorDto errorDto){ super(errorDto); }

    public CreateUsersRes(UserDto user){
        this.user = user;

    }
}
