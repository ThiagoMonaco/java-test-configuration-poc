package com.testconfigurationpoc.domain.service;

import com.testconfigurationpoc.domain.projection.BasicUserData;
import com.testconfigurationpoc.dto.CreateUserRequestDto;
import com.testconfigurationpoc.domain.entity.User;

import java.util.List;

public interface IUserService {
    User createUser(CreateUserRequestDto request);

    BasicUserData getUserById(Long id);

    List<User> getAllUsers();

//    User updateUser(CreateUserRequestDto request, Long id);
//
//    void deleteUser(Long id);
}
