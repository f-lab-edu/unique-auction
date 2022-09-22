package com.uniqueAuction.exception.advice.product;


import com.uniqueAuction.exception.advice.login.LoginException;
import com.uniqueAuction.exception.advice.login.LoginValidationException;
import com.uniqueAuction.web.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.uniqueAuction.web.product.controller")
public class ProductControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductException.class) // 이컨트롤러에서 이 예외가 발생하면 여기에서 잡힘
    public CommonResponse ProductExHandler(ProductException e) {
        log.error("[exceptionHandler] ex:", e);
        return CommonResponse.fail(e.getCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductValidationException.class)
    public CommonResponse ProductValidatedExHandler(ProductValidationException e) {
        log.error("[exceptionHandler] ex", e);
        return CommonResponse.fail(e.getMessage());
    }
}
