package com.uniqueauction.exception.advice;

import com.uniqueauction.exception.ErrorCode;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
	private final ErrorCode error;

	public CommonException(ErrorCode error) {
		this.error = error;
	}
}
