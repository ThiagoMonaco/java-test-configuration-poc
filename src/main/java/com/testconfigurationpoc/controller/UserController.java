package com.testconfigurationpoc.controller;

import com.testconfigurationpoc.domain.projection.BasicUserData;
import com.testconfigurationpoc.dto.CreateUserRequestDto;
import com.testconfigurationpoc.domain.entity.User;
import com.testconfigurationpoc.domain.service.IUserService;
import com.testconfigurationpoc.dto.CreateUserResponseDto;
import com.testconfigurationpoc.dto.UpdateUserRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/user/create")
    public CreateUserResponseDto createUser (@RequestBody CreateUserRequestDto createUserRequestDto) {
        return userService.createUser(createUserRequestDto);
    }

    @GetMapping("/user/id/{id}")
    public BasicUserData getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/all")
    public List<BasicUserData> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/user/update/{id}")
    public void updateUserById(@PathVariable Long id, @RequestBody UpdateUserRequestDto user) {
        userService.updateUsername(id, user.getUsername());
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
