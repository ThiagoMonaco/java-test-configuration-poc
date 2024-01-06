package com.testconfigurationpoc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Username should have more than three characters")
public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException() {
        super("Username should have more than three characters");
    }
}
