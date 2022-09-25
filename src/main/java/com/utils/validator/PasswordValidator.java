package com.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.utils.annotation.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	private String message;

	@Override
	public void initialize(Password constraintAnnotation) {
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value == null) {
			message = "암호는 필수값 입니다.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}

		Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$");
		Matcher matcher = pattern.matcher(value);

		if (!matcher.matches()) {
			message = "암호 형식에 맞지 않습니다.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}

		return true;
	}
}
