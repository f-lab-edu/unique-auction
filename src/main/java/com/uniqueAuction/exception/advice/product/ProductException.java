package com.uniqueAuction.exception.advice.product;

import com.uniqueAuction.exception.ErrorCode;

public class ProductException extends RuntimeException {


    private final ErrorCode errorCode;


    public ProductException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getCode() {
        return this.errorCode;
    }
}
