package com.uniqueAuction.web.response;

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

    public static CommonResponse success() {
        return new CommonResponse(true, null, null);
    }

    public static <T>CommonResponse success(T data) {
        return new CommonResponse(true, data, null);
    }

    public static CommonResponse fail(ErrorResponse errorResponse) {
        return new CommonResponse(false, null, errorResponse);
    }
}