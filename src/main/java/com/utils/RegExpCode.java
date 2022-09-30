package com.utils;

public enum RegExpCode {
    EMAIL("이메일은 필수값 입니다", "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", "이메일 형식에 맞지 않습니다."),
    PHONE("휴대폰 번호는 필수값 입니다.", "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", "휴대폰 번호 형식에 맞지 않습니다."),
    PASSWORD("암호는 필수값 입니다.", "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$", "암호 형식에 맞지 않습니다."),
    USERNAME("유저명은 필수값 입니다.", "^[A-Za-z0-9]{4,12}$", "유저명 형식에 맞지 않습니다."),
    ;



    private final String nullCheckMsg;
    private final String regExp;
    private final String regExpCheckMsg;

    RegExpCode(String nullCheckMsg, String regExp, String regExpCheckMsg) {
        this.nullCheckMsg = nullCheckMsg;
        this.regExp = regExp;
        this.regExpCheckMsg = regExpCheckMsg;
    }

    public String getNullCheckMsg() {
        return this.nullCheckMsg;
    }

    public String getRegExp() {
        return regExp;
    }

    public String getRegExpCheckMsg() {
        return this.regExpCheckMsg;
    }
}
