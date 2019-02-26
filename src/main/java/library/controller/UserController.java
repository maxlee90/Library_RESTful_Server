package library.controller;

import javax.validation.Valid;
import library.application.CreateUsersReq;
import library.application.CreateUsersRes;
import library.application.ReadUsersRes;
import library.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("userController")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("users")
public class UserController extends BaseController{
    private final UserService userService;

    @GetMapping
    public ReadUsersRes getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public CreateUsersRes createUser(@Valid @RequestBody CreateUsersReq req){
        return userService.createUser(req);
    }

}
