package com.uniqueAuction.exception.advice;

import com.uniqueAuction.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CommonValidationException extends RuntimeException {

    private final ErrorCode error;

    public CommonValidationException(ErrorCode error) {
        this.error = error;
    }
}
