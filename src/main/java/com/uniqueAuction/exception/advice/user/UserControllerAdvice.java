//package com.uniqueAuction.exception.advice.user;
//
//import com.uniqueAuction.web.response.CommonResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@Slf4j
//@RestControllerAdvice("com.uniqueAuction.web.user.controller")
//public class UserControllerAdvice {
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(UserException.class)
//    public CommonResponse UserValidatedExHandler(UserException e) {
//        log.error("[exceptionHandler] ex", e);
//        return new CommonResponse(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
//    }
//}
