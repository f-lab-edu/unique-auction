package com.uniqueAuction.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uniqueAuction.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
public class CommonResponse<T> {

    private int code;
    private String status;
    private String message;
    private T data;

    public CommonResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public CommonResponse(String status, ErrorCode errorCode) {
        this.status = status;
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static CommonResponse fail(String status, ErrorCode errorCode) {
        return new CommonResponse(status, errorCode);
    }

    public static CommonResponse fail(String status, String message) {
        return new CommonResponse(status, message);
    }


    public static CommonResponse success(ErrorCode errorCode) {
        return new CommonResponse(HttpStatus.OK.toString(), errorCode);
    }
}
