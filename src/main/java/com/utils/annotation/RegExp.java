package com.utils.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.utils.RegExpCode;
import com.utils.validator.RegExpValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegExpValidator.class)
@Documented
public @interface RegExp {

	String message() default "형식이 잘못 되었습니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	RegExpCode regExpCode();

}
