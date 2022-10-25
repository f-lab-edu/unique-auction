package com.uniqueauction.domain.aop;

import static com.uniqueauction.exception.ErrorCode.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.uniqueauction.exception.advice.CommonValidationException;

import lombok.extern.log4j.Log4j2;

@Component
@Configuration
@EnableAspectJAutoProxy
@Aspect
@Log4j2
public class ValidationCheckAspect {
	@Around("execution(* com.uniqueauction.web..*Controller.*(..))")
	public void validaiontCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		System.out.println("Validation Aop Start!!!");

		Object[] args = proceedingJoinPoint.getArgs();

		for (Object arg : args) {
			if (arg instanceof BindingResult) {
				checkError((BindingResult)arg);
			}
		}
	}

	private void checkError(BindingResult result) {
		if (result.hasErrors()) {
			String defaultMessage = result.getFieldError().getDefaultMessage();
			throw new CommonValidationException(MISSING_PARAMETER.setMissingParameterMsg(defaultMessage));
		}
	}

}
