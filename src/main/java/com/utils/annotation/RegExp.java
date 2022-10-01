package com.utils.annotation;


import com.utils.RegExpCode;
import com.utils.validator.RegExpValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

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
