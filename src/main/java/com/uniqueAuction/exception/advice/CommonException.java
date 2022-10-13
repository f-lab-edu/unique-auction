package com.uniqueAuction.exception.advice;

import com.uniqueAuction.exception.ErrorCode;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
	private final ErrorCode error;

	public CommonException(ErrorCode error) {
		this.error = error;
	}
}
