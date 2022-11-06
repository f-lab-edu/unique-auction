package com.uniqueauction.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

	private boolean success;
	private T data;
	private ErrorResponse error;

	public static <T> CommonResponse<T> success() {
		return new CommonResponse<>(true, null, null);
	}

	public static <T> CommonResponse<T> success(T data) {
		return new CommonResponse<>(true, data, null);
	}

	public static <T> CommonResponse<T> fail(ErrorResponse errorResponse) {
		return new CommonResponse<>(false, null, errorResponse);
	}
}
