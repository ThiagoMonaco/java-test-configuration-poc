package com.simplecrud.domain.service;

import com.simplecrud.data.dto.CreateUserRequestDto;
import com.simplecrud.domain.entity.User;

public interface IUserService {
    User createUser(CreateUserRequestDto request);
}
