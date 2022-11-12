package com.uniqueauction.domain.login.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.user.entity.Role;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.domain.user.service.EncryptService;
import com.uniqueauction.web.login.request.LoginRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService {

	private final UserRepository userRepository;
	private final HttpSession session;
	private final EncryptService encryptService;

	public void login(LoginRequest loginRequest) {

		User byEmailAndPassword = userRepository.findByEmailAndEncodedPassword(loginRequest.getEmail(),
			encryptService.encrypt(loginRequest.getPassword()));

		Role.setSession(session, byEmailAndPassword);
	}
}
