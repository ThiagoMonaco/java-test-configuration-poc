package data.services;

import com.testconfigurationpoc.domain.service.IValidatorService;
import configuration.CustomTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CustomTestConfiguration.class)
public class ValidatorServiceTest {

    @Autowired
    IValidatorService validatorService;

    @Test
    @DisplayName("Should return true if string has more than three characters")
    void shouldReturnTrueIfStringHasMoreThanThreeCharacters() {
        String string = "1234";

        boolean result = validatorService.hasMoreThanThreeCharacters(string);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if string has less than three characters")
    void shouldReturnFalseIfStringHasLessThanThreeCharacters() {
        String string = "12";

        boolean result = validatorService.hasMoreThanThreeCharacters(string);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Should return false if value is null")
    void shouldReturnFalseIfValueIsNull() {
        String string = null;

        boolean result = validatorService.hasMoreThanThreeCharacters(string);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Should return true if password have 8 letters, 1 uppercase letter and 1 number")
    void shouldReturnTrueIfPasswordHave8Letters1UppercaseLetterAnd1Number() {
        String password = "Abcdefg1";

        boolean result = validatorService.validatePassword(password);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Should return false if password have less than 8 letters")
    void shouldReturnFalseIfPasswordHaveLessThan8Letters() {
        String password = "Abcde12";

        boolean result = validatorService.validatePassword(password);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Should return false if password have less than 1 uppercase letter")
    void shouldReturnFalseIfPasswordHaveLessThan1UppercaseLetter() {
        String password = "abcdefg1";

        boolean result = validatorService.validatePassword(password);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Should return false if password have less than 1 number")
    void shouldReturnFalseIfPasswordHaveLessThan1Number() {
        String password = "Abcdefgh";

        boolean result = validatorService.validatePassword(password);

        Assertions.assertFalse(result);
    }
}
