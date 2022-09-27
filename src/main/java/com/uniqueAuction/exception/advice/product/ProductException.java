package com.uniqueAuction.exception.advice.product;

import com.uniqueAuction.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {
    private final ErrorCode error;
    public ProductException(ErrorCode error) {
        this.error = error;
    }
}
