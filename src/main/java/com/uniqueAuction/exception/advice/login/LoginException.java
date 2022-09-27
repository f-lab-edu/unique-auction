package com.uniqueAuction.exception.advice.login;

import com.uniqueAuction.exception.ErrorCode;
import lombok.Getter;

@Getter
public class LoginException extends RuntimeException {
    private final ErrorCode error;
    public LoginException(ErrorCode error) {
        this.error = error;
    }
}
