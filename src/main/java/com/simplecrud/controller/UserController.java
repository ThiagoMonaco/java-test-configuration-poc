package com.simplecrud.controller;

import com.simplecrud.domain.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user")
    public User GetUserData () {
        User user = new User("username", "password", null, null);
        return user;
    }
}
