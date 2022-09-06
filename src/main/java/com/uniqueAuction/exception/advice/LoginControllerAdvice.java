package com.uniqueAuction.exception.advice;


import com.uniqueAuction.exception.LoginException;
import com.uniqueAuction.exception.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.uniqueAuction.web.user.controller")
public class LoginControllerAdvice {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LoginException.class) // 이컨트롤러에서 이 예외가 발생하면 여기에서 잡힘
    public ResultMsg illegalExHandler(LoginException e) {
        log.error("[exceptionHandler] ex", e);
        return new ResultMsg(HttpStatus.NOT_FOUND.toString(), e.getMessage());
    }
}
