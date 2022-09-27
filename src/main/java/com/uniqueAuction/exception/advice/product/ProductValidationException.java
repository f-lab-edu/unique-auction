package com.uniqueAuction.exception.advice.product;

import com.uniqueAuction.exception.ErrorCode;
import com.uniqueAuction.web.response.ErrorResponse;
import lombok.Getter;

@Getter
public class ProductValidationException extends RuntimeException {
    private final ErrorResponse errorResponse;
    public ProductValidationException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
