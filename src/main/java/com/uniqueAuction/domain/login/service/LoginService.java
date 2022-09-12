package com.uniqueAuction.domain.login.service;


import com.uniqueAuction.domain.user.entity.Role;
import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.user.repository.UserRepository;
import com.uniqueAuction.exception.advice.login.LoginException;
import com.uniqueAuction.web.login.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;
    private final HttpSession session;


    public User login(LoginRequest loginRequest) {

        List<User> users = userRepository.findAll();
        System.out.println("@@@@@@@@@@@@@@@@@@@");
        User findUser = users.stream()
                .filter(user -> user.getEmail().equals(loginRequest.getEmail())
                        && user.getPassword().equals(loginRequest.getPassword()))
                .findFirst()
                .orElseThrow(() -> new LoginException("가입하지 않은 이메일이거나 잘못된 비밀번호입니다."));

        System.out.println("@@@@@@@@@@@@@@@@@@@");

        Role.setSession(session, findUser);

        return findUser;
    }
}
