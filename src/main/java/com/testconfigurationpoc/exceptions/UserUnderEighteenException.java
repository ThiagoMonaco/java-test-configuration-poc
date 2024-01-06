package com.testconfigurationpoc.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "User age is under 18", code = org.springframework.http.HttpStatus.BAD_REQUEST)
public class UserUnderEighteenException extends RuntimeException {
    public UserUnderEighteenException() {
        super("User age is under 18");
    }
}
