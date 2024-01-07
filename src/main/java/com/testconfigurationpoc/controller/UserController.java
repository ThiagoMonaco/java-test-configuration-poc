package com.testconfigurationpoc.controller;

import com.testconfigurationpoc.dto.CreateUserRequestDto;
import com.testconfigurationpoc.domain.entity.User;
import com.testconfigurationpoc.domain.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/user/create")
    public User createUser (@RequestBody CreateUserRequestDto createUserRequestDto) {
        return userService.createUser(createUserRequestDto);
    }

    @GetMapping("/user/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

}
