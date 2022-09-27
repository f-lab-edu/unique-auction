package com.uniqueAuction.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    /*
     * 1. 에러코드분류체계(8) : 대분류(1) + 중분류(1) + 순번코드(4)
     * 1-1. 대분류(1) : 시스템영역
     *      - S : SYSTEM EXCEPTION
     *      - B : BUSINESS EXCEPTION
     *      - V : VALIDATION EXCEPTION
     *
     * 1-2. 중분류(1): 도메인 영역
     *      - U : USER
     *      - L : LOGIN
     *      - P : PRODUCT
     */

    NOT_FOUND_USER("BU0001", "가입하지 않은 이메일이거나 잘못된 비밀번호입니다."),
    NOT_FOUND_PRODUCT("BP0001", "해당 상품이 없습니다.")
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
