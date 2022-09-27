package com.uniqueAuction.exception.advice.login;


import com.uniqueAuction.exception.ErrorCode;
import com.uniqueAuction.web.response.CommonResponse;
import com.uniqueAuction.web.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.uniqueAuction.web.login.controller")
public class LoginControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LoginException.class) // 이컨트롤러에서 이 예외가 발생하면 여기에서 잡힘
    public CommonResponse LoginExHandler(LoginException e) {
        log.error("[exceptionHandler] ex:", e);
        return CommonResponse.fail(ErrorResponse.builder()
                .errorCode(e.getError().getCode())
                .errorMessage(e.getError().getMessage())
                .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginValidationException.class)
    public CommonResponse LoginValidatedExHandler(LoginValidationException e) {
        log.error("[exceptionHandler] ex", e);
        return CommonResponse.fail(e.getErrorResponse());
    }
}
