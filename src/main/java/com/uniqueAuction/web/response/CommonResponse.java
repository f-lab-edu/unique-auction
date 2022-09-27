package com.uniqueAuction.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uniqueAuction.domain.product.entity.Product;
import com.uniqueAuction.exception.ErrorCode;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
public class CommonResponse<T> {

    private int code;
    private String message;
    private T data;


    public CommonResponse(String message) {
        this.message = message;
    }

    public CommonResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public CommonResponse(T data) {
        this.data = data;
    }

    public static CommonResponse success(String msg) {
        return new CommonResponse(msg);
    }

    public static <T>CommonResponse success(T data) {
        return new CommonResponse(data);
    }

    public static CommonResponse fail(ErrorCode errorCode) {
        return new CommonResponse(errorCode);
    }

    public static CommonResponse fail(String message) {
        return new CommonResponse(message);
    }
}
