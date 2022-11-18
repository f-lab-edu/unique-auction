package com.utils;

import lombok.Getter;

@Getter
public enum RegExpCode {

	EMAIL("이메일은 필수값 입니다", "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", "이메일 형식에 맞지 않습니다."),
	PHONE("휴대폰 번호는 필수값 입니다.", "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", "휴대폰 번호 형식에 맞지 않습니다."),
	PASSWORD("암호는 필수값 입니다.", "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", "암호는 숫자와 문자를 포함한 최소 8자 이상이어야 합니다."),
	USERNAME("유저명은 필수값 입니다.", "^[A-Za-z0-9]{4,12}$", "유저명 형식에 맞지 않습니다."),
	REVIEW_CONTENT("리뷰내용은 필수값 입니다.", "^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|~!@#$%^&*()_+|<>?:{}|]{0,100}$",
		"내용은 한글 ,영문 ,숫자, 특수문자만 허용되고 글자 수는 100자로 제한됩니다.");

	private final String nullCheckMsg;
	private final String regExp;
	private final String regExpCheckMsg;

	RegExpCode(String nullCheckMsg, String regExp, String regExpCheckMsg) {
		this.nullCheckMsg = nullCheckMsg;
		this.regExp = regExp;
		this.regExpCheckMsg = regExpCheckMsg;
	}
}
