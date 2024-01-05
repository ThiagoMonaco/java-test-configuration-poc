package com.testconfigurationpoc.services;

import com.testconfigurationpoc.domain.service.IValidatorService;
import com.testconfigurationpoc.helpers.regex.Regex;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Service("validatorService")
public class ValidatorServiceImpl implements IValidatorService {

    @Override
    public boolean hasMoreThanThreeCharacters(String value) {
        return value != null && value.length() > 3;
    }

    @Override
    public boolean validatePassword(String value) {
        Pattern pattern = Pattern.compile(Regex.PASSWORD.getRegex());
        return pattern.matcher(value).matches();
    }

    @Override
    public boolean validateBirthDateIsOlderThanEighteen(LocalDate birthDate) {
        return birthDate != null && birthDate.isBefore(LocalDate.now().minusYears(18));
    }
}
