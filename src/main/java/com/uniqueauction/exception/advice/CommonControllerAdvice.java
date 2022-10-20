package com.uniqueauction.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.uniqueauction.web.response.CommonResponse;
import com.uniqueauction.web.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice("com.uniqueauction.web")
public class CommonControllerAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CommonException.class)
	public CommonResponse commonExHandler(CommonException ex) {
		log.error("[exceptionHandler] ex:", ex);
		return CommonResponse.fail(ErrorResponse.of(ex.getError()));

	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CommonNotFoundException.class)
	public CommonResponse commonNotFoundExHandler(CommonNotFoundException ex) {
		log.error("[exceptionHandler] ex:", ex);
		return CommonResponse.fail(ErrorResponse.of(ex.getError()));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CommonValidationException.class)
	public CommonResponse commonValidatedExHandler(CommonValidationException ex) {
		log.error("[exceptionHandler] ex", ex);
		return CommonResponse.fail(ErrorResponse.of(ex.getError()));
	}
}
