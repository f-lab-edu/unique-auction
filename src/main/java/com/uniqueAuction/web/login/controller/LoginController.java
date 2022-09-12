package com.uniqueAuction.web.login.controller;


import com.uniqueAuction.domain.login.service.LoginService;
import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.exception.advice.login.LoginValidationException;
import com.uniqueAuction.web.login.request.LoginRequest;
import com.uniqueAuction.web.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public CommonResponse signIn(@RequestBody @Validated LoginRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new LoginValidationException(result.getFieldError().getDefaultMessage());
        }

        User login = loginService.login(request);

        return CommonResponse.success("로그인 성공", login);
    }

}
