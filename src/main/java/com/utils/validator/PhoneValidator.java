package com.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.utils.annotation.Phone;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	private String message;

	@Override
	public void initialize(Phone constraintAnnotation) {
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value == null) {
			message = "휴대폰 번호는 필수값 입니다.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}

		Pattern pattern = Pattern.compile("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$");
		Matcher matcher = pattern.matcher(value);
		
		if (!matcher.matches()) {
			message = "휴대폰 번호 형식에 맞지 않습니다.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}

		return true;
	}
}
