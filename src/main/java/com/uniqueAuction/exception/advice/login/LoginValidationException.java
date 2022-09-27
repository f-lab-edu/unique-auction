package com.uniqueAuction.exception.advice.login;

import com.uniqueAuction.web.response.ErrorResponse;
import lombok.Getter;

@Getter
public class LoginValidationException extends RuntimeException {
    private final ErrorResponse errorResponse;
    public LoginValidationException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
