package com.uniqueAuction.domain.login.service;


import com.uniqueAuction.domain.user.entity.Role;
import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.user.repository.UserRepository;
import com.uniqueAuction.domain.user.service.EncryptService;
import com.uniqueAuction.exception.advice.CommonNotFoundException;
import com.uniqueAuction.web.login.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.uniqueAuction.exception.ErrorCode.NOT_FOUND_USER;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;
    private final HttpSession session;
    private final EncryptService encryptService;


    public void login(LoginRequest loginRequest) {

        List<User> users = userRepository.findAll();

        User findUser = users.stream()
                .filter(user -> user.getEmail().equals(loginRequest.getEmail())
                        && user.getEncodedPassword().equals(encryptService.encrypt(loginRequest.getPassword())))
                .findFirst()
                .orElseThrow(() -> new CommonNotFoundException(NOT_FOUND_USER));

        Role.setSession(session, findUser);
    }
}
