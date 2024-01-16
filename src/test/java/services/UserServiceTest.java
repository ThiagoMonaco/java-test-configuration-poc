package services;

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
import configuration.BasicTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import stubs.domain.projection.BasicUserDataStub;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static stubs.domain.entity.UserStub.getUserStub;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BasicTestConfiguration.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @SpyBean
    @Qualifier("validatorServiceStub")
    private IValidatorService validatorServiceStub;

    @SpyBean
    @Qualifier("dateMapperStub")
    private IDateMapper dateMapperStub;

    @SpyBean
    @Qualifier("userRepositoryStub")
    private UserRepository userRepositoryStub;

    private CreateUserRequestDto mockCreateUserRequestDto() {
        return new CreateUserRequestDto(
                "username",
                "password",
                "01/01/2000");
    }

    @Test
    @DisplayName("Should call username validation with correct parameters")
    void shouldCallUsernameValidationWithCorrectParameters() {
        CreateUserRequestDto userDto = mockCreateUserRequestDto();

        userService.createUser(userDto);

        verify(validatorServiceStub, times(1)).hasMoreThanThreeCharacters(userDto.getUsername());
    }

    @Test
    @DisplayName("Should throw exception if username is invalid")
    void shouldThrowExceptionIfUsernameIsInvalid() {
        CreateUserRequestDto userDto = mockCreateUserRequestDto();

        when(validatorServiceStub.hasMoreThanThreeCharacters(userDto.getUsername())).thenReturn(false);

        Exception exception = assertThrows(InvalidUsernameException.class, () -> {
            userService.createUser(userDto);
        });

        assertEquals("Username should have more than three characters", exception.getMessage());
    }

    @Test
    @DisplayName("Should call birthdate validation with correct parameters")
    void shouldCallValidateBirthDateWithCorrectParams() {
        CreateUserRequestDto userDto = mockCreateUserRequestDto();
        LocalDate expectedLocalDate = LocalDate.of(2000, 1, 1);
        when(dateMapperStub.mapStringToLocalDate(userDto.getBirthDate())).thenReturn(expectedLocalDate);

        userService.createUser(userDto);

        verify(validatorServiceStub, times(1)).validateBirthDateIsOlderThanEighteen(expectedLocalDate);
    }

    @Test
    @DisplayName("Should throw if birthdate validation throws")
    void shouldThrowIfBirthdateValidationThrows() {
        CreateUserRequestDto userDto = mockCreateUserRequestDto();
        doThrow(new RuntimeException("Birthdate is invalid"))
                .when(validatorServiceStub)
                .validateBirthDateIsOlderThanEighteen(any());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.createUser(userDto);
        });

        assertEquals("Birthdate is invalid", exception.getMessage());
    }

    @Test
    @DisplayName("Should call save user with correct params")
    void shouldCallSaveUserWithCorrectParams() {
        CreateUserRequestDto userDto = mockCreateUserRequestDto();
        userService.createUser(userDto);
        verify(userRepositoryStub, times(1)).save(any());
    }

    @Test
    @DisplayName("Should throw if save user throws")
    void shouldThrowIfSaveUserThrows() {
        CreateUserRequestDto userDto = mockCreateUserRequestDto();
        doThrow(new RuntimeException("Test Error"))
                .when(userRepositoryStub)
                .save(any());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.createUser(userDto);
        });

        assertEquals("Test Error", exception.getMessage());
    }

    @Test
    @DisplayName("Should return user correctly")
    void shouldReturnUserIfEverythingIsOk() {
        CreateUserRequestDto userDto = mockCreateUserRequestDto();

        CreateUserResponseDto result = userService.createUser(userDto);

        assertEquals(userDto.getUsername(), result.getUsername());
        assertNotNull(result.getId());
    }

    @Test
    @DisplayName("Should throw invalidPasswordException if password is invalid")
    void shouldThrowInvalidPasswordExceptionIfPasswordIsInvalid() {
        CreateUserRequestDto userDto = mockCreateUserRequestDto();
        when(validatorServiceStub.validatePassword(userDto.getPassword())).thenReturn(false);
        Exception exception = assertThrows(InvalidPasswordException.class, () -> {
            userService.createUser(userDto);
        });
        assertEquals("Invalid password", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw UserUnderEighteenException if user age is under 18")
    void shouldThrowUserUnderEighteenExceptionIfUserAgeIsUnderEighteen() {
        CreateUserRequestDto userDto = mockCreateUserRequestDto();

        when(validatorServiceStub.validateBirthDateIsOlderThanEighteen(any())).thenReturn(false);

        Exception exception = assertThrows(UserUnderEighteenException.class, () -> {
            userService.createUser(userDto);
        });
        assertEquals("User age is under 18", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw 404 user not found exception if user is not found")
    void shouldReturn404NullIfUserIsNotFound() {
        when(userRepositoryStub.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(1L);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should return user if user is found")
    void shouldReturnUserIfUserIsFound() {
        User expectedUserData = getUserStub();
        BasicUserData expectedUser = BasicUserDataStub
                .builder()
                .id(expectedUserData.getId())
                .username(expectedUserData.getUsername())
                .birthDate(expectedUserData.getBirthDate())
                .build();

        when(userRepositoryStub.findUserById(1L)).thenReturn(Optional.of(expectedUser));

        BasicUserData result = userService.getUserById(1L);

        assertEquals(expectedUser, result);
    }

    @Test
    @DisplayName("Should return all users correctly")
    void shouldReturnAllUsersCorrectly() {
        User expectedUserData = getUserStub();
        List<BasicUserData> expectedUsers = List.of(
                BasicUserDataStub
                        .builder()
                        .username(expectedUserData.getUsername())
                        .id(expectedUserData.getId())
                        .build()
        );
        when(userRepositoryStub.findAllBy()).thenReturn(expectedUsers);

        List<BasicUserData> result = userService.getAllUsers();

        assertEquals(expectedUsers, result);
    }

    @Test
    @DisplayName("Should call findAll once")
    void shouldCallFindAllOnce() {
        userService.getAllUsers();
        verify(userRepositoryStub, times(1)).findAllBy();
    }

    @Test
    @DisplayName("Should return empty list if no users are found")
    void shouldReturnEmptyListIfNoUsersAreFound() {
        when(userRepositoryStub.findAllBy()).thenReturn(List.of());

        List<BasicUserData> result = userService.getAllUsers();

        assertEquals(List.of(), result);
    }

    @Test
    @DisplayName("Should throw error if username is invalid")
    void shouldThrowErrorIfUsernameIsInvalid() {
        when(validatorServiceStub.hasMoreThanThreeCharacters("a")).thenReturn(false);

        Exception exception = assertThrows(InvalidUsernameException.class, () -> {
            userService.updateUsername(1L, "a");
        });
        assertEquals("Username should have more than three characters", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw error if user is not found")
    void shouldThrowErrorIfUserIsNotFound() {
        when(userRepositoryStub.findUserById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.updateUsername(1L, "username");
        });
        assertEquals("User not found", exception.getMessage());
    }
}
