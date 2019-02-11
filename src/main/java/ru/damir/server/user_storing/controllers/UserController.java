package ru.damir.server.user_storing.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.damir.server.user_storing.controllers.response.UserResponse;
import ru.damir.server.user_storing.dao.UserDao;

@RestController
@RequestMapping(value = "users", produces = MediaTypes.HAL_JSON_VALUE)
public class UserController {


    @GetMapping
    public Resources<UserResponse> getUser(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "accountNumber", required = false) int accountNumber) {

        return null;
    }
}
