package com.uniqueAuction.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CommonResponse<T> {
    private String code;
    private String message;
    private T data;

    public CommonResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> CommonResponse success(String message, T data) {
        return new CommonResponse(HttpStatus.OK.toString(), message, data);
    }

    public static <T> CommonResponse success(String message) {
        return new CommonResponse(HttpStatus.OK.toString(), message);
    }

    public static <T> CommonResponse success(String code, String message) {
        return new CommonResponse(code, message);
    }

    public static CommonResponse fail(String code, String message) {
        return new CommonResponse(code, message);
    }
}
