package com.testconfigurationpoc.services;

import com.testconfigurationpoc.domain.projection.BasicUserData;
import com.testconfigurationpoc.dto.CreateUserRequestDto;
import com.testconfigurationpoc.dto.CreateUserResponseDto;
import com.testconfigurationpoc.exceptions.InvalidPasswordException;
import com.testconfigurationpoc.exceptions.InvalidUsernameException;
import com.testconfigurationpoc.exceptions.UserNotFoundException;
import com.testconfigurationpoc.exceptions.UserUnderEighteenException;
import com.testconfigurationpoc.repository.UserRepository;
import com.testconfigurationpoc.domain.entity.User;
import com.testconfigurationpoc.domain.mapper.IDateMapper;
import com.testconfigurationpoc.domain.service.IUserService;
import com.testconfigurationpoc.domain.service.IValidatorService;
import jakarta.transaction.Transactional;
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
    public CreateUserResponseDto createUser(CreateUserRequestDto request) {
        if(!validatorService.hasMoreThanThreeCharacters(request.getUsername())) {
            throw new InvalidUsernameException();
        }
        if(!validatorService.validatePassword(request.getPassword())) {
            throw new InvalidPasswordException();
        }
        LocalDate birthDateLocalDate = dateMapper.mapStringToLocalDate(request.getBirthDate());

        if(!validatorService.validateBirthDateIsOlderThanEighteen(birthDateLocalDate)) {
            throw new UserUnderEighteenException();
        };
        User result = userRepository.save(User
                .builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .birthDate(birthDateLocalDate)
                .favoriteTechs(List.of())
                .build()
        );

        return CreateUserResponseDto
                .builder()
                .id(result.getId())
                .username(result.getUsername())
                .build();
    }

    @Override
    public BasicUserData getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<BasicUserData> getAllUsers() {
        return userRepository.findAllBy();
    }

    @Override
    @Transactional
    public void updateUsername(Long id, String username) {
        if(!validatorService.hasMoreThanThreeCharacters(username)) {
            throw new InvalidUsernameException();
        }

        if(userRepository.findUserById(id).isEmpty()) {
            throw new UserNotFoundException();
        };

        userRepository.updateUsernameById(username, id);
    }
}
