package library.application;

import java.util.List;
import library.common.ErrorDto;
import library.common.UserDto;
import lombok.Getter;

@Getter
public class ReadUsersRes extends BaseResponse{
    List<UserDto> users;

    public ReadUsersRes(ErrorDto errorDto){ super(errorDto); }
    public ReadUsersRes(List<UserDto> users){
        this.users = users;
    }
}
