package com.uniqueAuction.web.login.controller;


import com.uniqueAuction.domain.login.service.LoginService;
import com.uniqueAuction.exception.advice.login.LoginValidationException;
import com.uniqueAuction.web.login.request.LoginRequest;
import com.uniqueAuction.web.response.CommonResponse;
import com.uniqueAuction.web.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;

/**
 * Validated :  검증기 실행 애노테이션 WebDataBinder에 등록한 검증기 찾아서 실행 / 스프링에서 제공하는 어노테이션 및 기능 .
 * 동작원리 :  AOP 기반으로 유효성 검증 기반으로 인터셉터 등록, 해당 객체가 호출 될때 AOP 포인트 컷으로 요청을 거로채서 유효성 검증.
 * validated에 의한 예외는 ConstraintViloationException 예외가 해당됨.
 * 만약 애노테이션을 쓰지않으면 직접  커스텀 벨리데이터를 등록해서 주입시켜야됨.
 * BeanValidation : 검증 로직을 모든 프로젝트에 적용하게 공통화 표준화 한 것.
 * Bean Validation 2.0 이라는 기술표준  검증 애노테이션과 여러 인터페이스의 모음
 * HttpMessageConverter 단계에서 객체를 생성하지못하면 예외발생 .
 */


@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public CommonResponse signIn(@RequestBody @Validated LoginRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new LoginValidationException(
                    ErrorResponse.builder()
                            .errorCode("VP0001")
                            .errorMessage(Objects.requireNonNull(result.getFieldError()).getDefaultMessage())
                            .build());
        }

        loginService.login(request);

        return CommonResponse.success();
    }
}
