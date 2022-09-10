package com.uniqueAuction.exception.advice.login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.uniqueAuction.web.response.CommonResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice("com.uniqueAuction.web.user.controller")
public class LoginControllerAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(LoginException.class) // 이컨트롤러에서 이 예외가 발생하면 여기에서 잡힘
	public CommonResponse LoginExHandler(LoginException e) {
		log.error("[exceptionHandler] ex", e);
		return new CommonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public CommonResponse LoginValidatedExHandler(IllegalArgumentException e) {
		log.error("[exceptionHandler] ex", e);
		return new CommonResponse(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
	}
}
