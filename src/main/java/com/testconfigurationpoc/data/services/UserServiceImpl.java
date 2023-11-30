package com.testconfigurationpoc.data.services;

import com.testconfigurationpoc.data.dto.CreateUserRequestDto;
import com.testconfigurationpoc.data.repository.UserRepository;
import com.testconfigurationpoc.domain.entity.User;
import com.testconfigurationpoc.domain.mapper.IDateMapper;
import com.testconfigurationpoc.domain.service.IUserService;
import com.testconfigurationpoc.domain.service.IValidatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IValidatorService validatorService;
    private final IDateMapper dateMapper;
    private final UserRepository userRepository;


    @Override
    public User createUser(CreateUserRequestDto request) {
        if(!validatorService.hasMoreThanThreeCharacters(request.getUsername())) {
            throw new RuntimeException("Username should have more than three characters");
        }
        validatorService.validatePassword(request.getPassword());
        LocalDate birthDateLocalDate = dateMapper.mapStringToLocalDate(request.getBirthDate());
        validatorService.validateBirthDateIsOlderThanEighteen(birthDateLocalDate);
        User result = userRepository.save(User
                .builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .birthDate(birthDateLocalDate)
                .favoriteTechs(List.of())
                .build()
        );

        return result;
    }
}
