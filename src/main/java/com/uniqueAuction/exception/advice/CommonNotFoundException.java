package com.uniqueAuction.exception.advice;

import com.uniqueAuction.exception.ErrorCode;

import lombok.Getter;

@Getter
public class CommonNotFoundException extends RuntimeException {
	private final ErrorCode error;

	public CommonNotFoundException(ErrorCode error) {
		this.error = error;
	}
}
