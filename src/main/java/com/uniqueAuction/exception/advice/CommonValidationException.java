package com.uniqueauction.exception.advice;

import com.uniqueauction.exception.ErrorCode;

import lombok.Getter;

@Getter
public class CommonValidationException extends RuntimeException {

	private final ErrorCode error;

	public CommonValidationException(ErrorCode error) {
		this.error = error;
	}
}
