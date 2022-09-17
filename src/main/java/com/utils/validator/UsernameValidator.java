package com.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.utils.annotation.Username;

public class UsernameValidator implements ConstraintValidator<Username, String> {

	private String message;

	@Override
	public void initialize(Username constraintAnnotation) {
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value == null) {
			message = "유저명은 필수값 입니다.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}

		Pattern pattern = Pattern.compile("^[A-Za-z0-9]{4,12}$");
		Matcher matcher = pattern.matcher(value);

		if (!matcher.matches()) {
			message = "유저명 형식에 맞지 않습니다.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}

		return true;
	}
}
