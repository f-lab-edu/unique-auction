package com.uniqueAuction.exception;

public enum ErrorCode {

    LOGIN_SUCCESS(1000, "로그인 성공"),
    NOT_FOUND_USER(1001, "가입하지 않은 이메일이거나 잘못된 비밀번호입니다."),





    PRODUCT_UPDATE_SUCCESS(2001, "상품을 수정하였습니다."),
    NOT_FOUND_PRODUCT(2002, "해당 상품이 없습니다.")
    ;


    private int code;
    private String message;


    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return this.message;
    }
}
