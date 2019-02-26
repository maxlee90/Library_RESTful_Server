package library.service;

import java.util.ArrayList;
import java.util.List;
import library.application.CreateUsersReq;
import library.application.CreateUsersRes;
import library.application.ReadUsersRes;
import library.common.*;
import library.domain.user.User;
import library.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserService extends BaseService {
    private final UserRepository userRepository;

    private final DtoMapper dtoMapper;

    public ReadUsersRes getAllUsers(){
        return new ReadUsersRes( dtoMapper.mapToUserDtos(userRepository.findAll()));
    }

    public CreateUsersRes createUser(CreateUsersReq req){

        User user = userRepository.findByFirstName(req.getUser().getFirstName());
        if (user != null){
            log.info("duplicated : {}", user.toString());
            return new CreateUsersRes(new ErrorDto(ErrorCode.ALEADY_EXIST_USER,null));
        }

        user = new User();
        user.setFirstName(req.getUser().getFirstName());
        user.setLastName(req.getUser().getLastName());
        user.setAddress(req.getUser().getAddress());
        user.setType(req.getUser().getType());

        userRepository.save(user);

        return new CreateUsersRes(dtoMapper.mapToDto(user));
    }
}
