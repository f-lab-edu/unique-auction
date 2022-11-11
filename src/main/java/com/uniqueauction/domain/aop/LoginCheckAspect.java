package com.uniqueauction.domain.aop;

import static com.uniqueauction.exception.ErrorCode.*;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.uniqueauction.exception.advice.CommonException;
import com.utils.SessionUtil;

import lombok.extern.log4j.Log4j2;

/**
 * 현재 로그인한 아이디를 체크해주는 AOP
 * 현재 로그인한 아이디와 다른 아이디가 들어오면
 * 세션에서 아이디 확인후 불일치시 권한 없음을 내보내준다.
 * 컨트롤러 메서드에  맨 첫번째 파라미터를 로그인 id 를 pathvariable로 지정해 준다.
 */

@Component
@Aspect
@Log4j2
public class LoginCheckAspect {

	private static final int ID_INDEX = 0;

	@Around("@annotation(com.uniqueauction.domain.aop.LoginCheck) && @ annotation(loginCheck)")
	public Object adminLoginCheck(ProceedingJoinPoint pjt, LoginCheck loginCheck) throws Throwable {
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest()
			.getSession();

		log.info("Login Checker AOP Start");

		String id = null;

		String userType = loginCheck.type().toString();

		id = roleCheck(session, userType);

		nullCheck(id);

		Object[] modifiedArgs = pjt.getArgs();

		if (modifiedArgs != null) {
			compareId(id, modifiedArgs);
		}

		return pjt.proceed(modifiedArgs);
	}

	private void compareId(String id, Object[] modifiedArgs) {
		if (!modifiedArgs[ID_INDEX].equals(id)) {
			throw new CommonException(UN_AUTHORIZED);
		}
	}

	private String roleCheck(HttpSession session, String userType) {

		String id = null;

		switch (userType) {
			case "ADMIN": {
				id = SessionUtil.getLoginAdminId(session);
				break;
			}
			case "CUSTOMER": {
				id = SessionUtil.getLoginMemberId(session);
				break;
			}
		}
		return id;
	}

	private void nullCheck(String id) {
		if (id == null) {
			throw new CommonException(UN_AUTHORIZED);
		}
	}

}
