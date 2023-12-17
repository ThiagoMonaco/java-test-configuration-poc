package data.services;

import com.testconfigurationpoc.data.dto.CreateUserRequestDto;
import com.testconfigurationpoc.data.repository.UserRepository;
import com.testconfigurationpoc.domain.entity.User;
import com.testconfigurationpoc.domain.mapper.IDateMapper;
import com.testconfigurationpoc.domain.service.IUserService;
import com.testconfigurationpoc.domain.service.IValidatorService;
import configuration.CustomTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CustomTestConfiguration.class)
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

        Exception exception = assertThrows(RuntimeException.class, () -> {
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

        User result = userService.createUser(userDto);

        assertEquals(userDto.getUsername(), result.getUsername());
        assertEquals(userDto.getPassword(), result.getPassword());
        assertEquals(LocalDate.of(2000, 1, 1), result.getBirthDate());
        assertEquals(List.of(), result.getFavoriteTechs());
        assertNotNull(result.getId());
    }
}
