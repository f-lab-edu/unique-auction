package com.uniqueauction.domain.login.service;

import static com.uniqueauction.exception.ErrorCode.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.domain.user.service.EncryptService;
import com.uniqueauction.exception.advice.CommonNotFoundException;
import com.uniqueauction.web.login.request.LoginRequest;
import com.utils.SessionUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService {

	private final UserRepository userRepository;
	private final HttpSession session;
	private final EncryptService encryptService;

	public void login(LoginRequest loginRequest) {

		User user = userRepository.findByEmailAndEncodedPassword(
				loginRequest.getEmail(), encryptService.encrypt(loginRequest.getPassword()))
			.orElseThrow(() -> new CommonNotFoundException(NOT_FOUND_USER));

		SessionUtil.setSession(session, user);
	}
}
