package com.uniqueauction.exception.advice;

import com.uniqueauction.exception.ErrorCode;

import lombok.Getter;

@Getter
public class CommonNotFoundException extends RuntimeException {
	private final ErrorCode error;

	public CommonNotFoundException(ErrorCode error) {
		this.error = error;
	}
}
