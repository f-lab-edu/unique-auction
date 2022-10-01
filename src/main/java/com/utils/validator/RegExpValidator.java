package com.utils.validator;

import com.utils.RegExpCode;
import com.utils.annotation.RegExp;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpValidator implements ConstraintValidator<RegExp, String> {

    private String message;
    private RegExpCode regExpCode;

    @Override
    public void initialize(RegExp constraintAnnotation) {
        regExpCode = constraintAnnotation.regExpCode();
        message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            message = regExpCode.getNullCheckMsg();
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        Pattern pattern = Pattern.compile(regExpCode.getRegExp());
        Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            message = regExpCode.getRegExpCheckMsg();
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        return true;
    }
}
