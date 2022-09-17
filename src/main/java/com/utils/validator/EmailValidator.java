package com.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.utils.annotation.Email;

public class EmailValidator implements ConstraintValidator<Email, String> {

	private String message;

	@Override
	public void initialize(Email constraintAnnotation) {
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value == null) {
			message = "이메일은 필수값 입니다.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}

		Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$");
		Matcher matcher = pattern.matcher(value);

		if (!matcher.matches()) {
			message = "이메일 형식에 맞지 않습니다.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}

		return true;
	}
}
