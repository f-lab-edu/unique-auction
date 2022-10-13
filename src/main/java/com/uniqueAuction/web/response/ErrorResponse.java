package com.uniqueAuction.web.response;

import com.uniqueAuction.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {
	private String errorCode;
	private String errorMessage;

	public ErrorResponse(ErrorCode errorCode) {
		this.errorCode = errorCode.getCode();
		this.errorMessage = errorCode.getMessage();
	}

	public static ErrorResponse of(ErrorCode errorCode) {
		return new ErrorResponse(errorCode);
	}
}
