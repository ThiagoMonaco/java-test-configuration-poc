package com.simplecrud.domain.service;

import java.time.LocalDate;

public interface IValidatorService {
    boolean hasMoreThanThreeCharacters(String value);
    boolean validatePassword(String value);
    boolean validateBirthDateIsOlderThanEighteen(LocalDate birthDate);
}
