package com.uniqueAuction.exception.advice.login;

public class LoginValidationException extends RuntimeException {

    public LoginValidationException() {
        super();
    }

    public LoginValidationException(String message) {
        super(message);
    }

    public LoginValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginValidationException(Throwable cause) {
        super(cause);
    }

    protected LoginValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
