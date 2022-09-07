package com.uniqueAuction.web.user.controller;


import com.uniqueAuction.domain.login.service.LoginService;
import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.exception.CommonResponse;
import com.uniqueAuction.web.user.dto.LoginRequest;
import com.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public CommonResponse signIn(@RequestBody @Validated LoginRequest request, BindingResult result, HttpSession session) {

        if (result.hasErrors()) {
            throw new IllegalArgumentException(result.getFieldError().getDefaultMessage());
        }

        User loginUser = loginService.login(request);

        if (loginUser.getRole().equals("ADMIN")) {
            SessionUtil.setLoginAdminId(session, loginUser.getUserId());
        } else {
            SessionUtil.setLoginMemberId(session, loginUser.getUserId());
        }

        return CommonResponse.success("로그인 성공");
    }

}
