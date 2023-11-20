package com.testconfigurationpoc.data.services;

import com.testconfigurationpoc.data.dto.CreateUserRequestDto;
import com.testconfigurationpoc.domain.entity.User;
import com.testconfigurationpoc.domain.mapper.IDateMapper;
import com.testconfigurationpoc.domain.service.IUserService;
import com.testconfigurationpoc.domain.service.IValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("userService")
public class UserServiceImpl implements IUserService {

    private final IValidatorService validatorService;
    private final IDateMapper dateMapper;


    @Autowired
    public UserServiceImpl(
            @Qualifier("validatorService") IValidatorService validatorService,
            IDateMapper dateMapper
    ) {
        this.validatorService = validatorService;
        this.dateMapper = dateMapper;
    }

    @Override
    public User createUser(CreateUserRequestDto request) {
        if(!validatorService.hasMoreThanThreeCharacters(request.getUsername())) {
            throw new RuntimeException("Username should have more than three characters");
        }
        validatorService.validatePassword(request.getPassword());
        LocalDate birthDateLocalDate = dateMapper.mapStringToLocalDate(request.getBirthDate());
        validatorService.validateBirthDateIsOlderThanEighteen(birthDateLocalDate);
        // repository create user data
        // return user data
        return null;
    }
}
