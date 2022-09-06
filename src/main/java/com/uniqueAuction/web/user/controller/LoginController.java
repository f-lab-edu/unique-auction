package com.uniqueAuction.web.user.controller;


import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.user.service.UserService;
import com.uniqueAuction.exception.ResultMsg;
import com.uniqueAuction.web.user.dto.LoginRequest;
import com.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResultMsg signIn(@RequestBody LoginRequest request, HttpSession session) {

        User loginUser = userService.login(request);

        if (loginUser.getRole().equals("ADMIN")) {
            SessionUtil.setLoginAdminId(session, loginUser.getUserId());
        } else {
            SessionUtil.setLoginMemberId(session, loginUser.getUserId());
        }

        return new ResultMsg(HttpStatus.OK.toString(), "로그인 성공");
    }

}
