package com.uniqueauction.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

	NOT_FOUND_USER("not_found_user", "가입하지 않은 이메일이거나 잘못된 비밀번호입니다."),
	NOT_FOUND_CATEGORY("not_found_category", "카테고리를 찾을 수 없습니다."),
	DUPLICATE_USER("duplicate_user", "이미 존재하는 이메일입니다."),
	MISSING_PARAMETER("missing_parameter", ""),
	ENCRYPT_ERROR("encrypt_error", "암호화 중 에러가 발생하였습니다"),
	NOT_FOUND_TRADE("not_found_trade", "거래 요청 데이터를 찾을 수 없습니다."),
	NOT_FOUND_PRODUCT("not_found_product", "등록되지 않은 상품입니다."),
	NOT_FOUND_IMAGE("not_found_image", "이미지를 찾을 수 없습니다."),
	LOCK_TIMEOUT("lock_time_out", "Lock time out.");

	private final String code;
	private String message;

	ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ErrorCode setMissingParameterMsg(String msg) {
		if (this.equals(MISSING_PARAMETER)) {
			setMessage(msg);
		}
		return this;
	}

	private void setMessage(String msg) {
		this.message = msg;
	}
}
