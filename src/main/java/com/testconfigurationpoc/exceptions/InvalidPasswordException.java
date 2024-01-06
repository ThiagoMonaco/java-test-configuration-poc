package com.testconfigurationpoc.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Invalid password", code = org.springframework.http.HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("Invalid password");
    }
}
