package com.uniqueAuction.exception.advice;


import com.uniqueAuction.web.response.CommonResponse;
import com.uniqueAuction.web.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.uniqueAuction.web")
public class CommonControllerAdvice {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommonException.class)
    public CommonResponse commonExHandler(CommonException e) {
        log.error("[exceptionHandler] ex:", e);
        return CommonResponse.fail(ErrorResponse.of(e.getError()));

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CommonNotFoundException.class)
    public CommonResponse commonNotFoundExHandler(CommonNotFoundException e) {
        log.error("[exceptionHandler] ex:", e);
        return CommonResponse.fail(ErrorResponse.of(e.getError()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommonValidationException.class)
    public CommonResponse commonValidatedExHandler(CommonValidationException e) {
        log.error("[exceptionHandler] ex", e);
        return CommonResponse.fail(ErrorResponse.of(e.getError()));
    }
}
