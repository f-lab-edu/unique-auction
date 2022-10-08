package com.uniqueAuction.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

	NOT_FOUND_USER("not_found_user", "가입하지 않은 이메일이거나 잘못된 비밀번호입니다."),
	DUPLICATE_USER("duplicate_user", "이미 존재하는 이메일입니다."),
	MISSING_PARAMETER("missing_parameter", ""),
	ENCRYPT_ERROR("encrypt_error", "암호화 중 에러가 발생하였습니다"),
	NOT_FOUND_SALE_BID("not_found_sale_bid", "즉시 구매가 불가능한 사이즈 입니다. 구매 입찰만 가능합니다."),
	NOT_FOUND_PURCHASE_BID("not_found_purchase_bid", "즉시 판매가 불가능한 사이즈 입니다. 판매 입찰만 가능합니다."),

	;

	private final String code;
	private final String message;

	ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public String getMessge() {
		return this.message;
	}
}
