package com.testconfigurationpoc.domain.service;

import com.testconfigurationpoc.dto.CreateUserRequestDto;
import com.testconfigurationpoc.domain.entity.User;

public interface IUserService {
    User createUser(CreateUserRequestDto request);
}
