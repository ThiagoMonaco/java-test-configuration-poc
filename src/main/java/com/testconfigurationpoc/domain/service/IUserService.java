package com.testconfigurationpoc.domain.service;

import com.testconfigurationpoc.domain.projection.BasicUserData;
import com.testconfigurationpoc.dto.CreateUserRequestDto;
import com.testconfigurationpoc.domain.entity.User;
import com.testconfigurationpoc.dto.CreateUserResponseDto;

import java.util.List;

public interface IUserService {
    CreateUserResponseDto createUser(CreateUserRequestDto request);

    BasicUserData getUserById(Long id);

    List<BasicUserData> getAllUsers();

    void updateUsername(Long id, String username);

    void deleteUser(Long id);
}
