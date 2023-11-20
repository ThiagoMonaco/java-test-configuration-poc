package com.testconfigurationpoc.domain.service;

import com.testconfigurationpoc.data.dto.CreateUserRequestDto;
import com.testconfigurationpoc.domain.entity.User;

public interface IUserService {
    User createUser(CreateUserRequestDto request);
}
