package com.uniqueAuction.exception.advice.login;

import com.uniqueAuction.exception.ErrorCode;

public class LoginException extends RuntimeException {


    private final ErrorCode errorCode;


    public LoginException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getCode() {
        return this.errorCode;
    }
}
