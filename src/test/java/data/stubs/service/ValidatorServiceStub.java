package data.stubs.service;

import com.testconfigurationpoc.domain.service.IValidatorService;
import org.springframework.boot.test.context.TestComponent;

import java.time.LocalDate;

@TestComponent
public class ValidatorServiceStub implements IValidatorService {
    @Override
    public boolean hasMoreThanThreeCharacters(String value) {
        return true;
    }

    @Override
    public boolean validatePassword(String value) {
        return true;
    }

    @Override
    public boolean validateBirthDateIsOlderThanEighteen(LocalDate birthDate) {
        return true;
    }
}
