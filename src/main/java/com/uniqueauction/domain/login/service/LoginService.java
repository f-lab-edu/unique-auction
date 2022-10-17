package com.uniqueauction.domain.login.service;

import static com.uniqueauction.exception.ErrorCode.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.user.entity.Role;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.domain.user.service.EncryptService;
import com.uniqueauction.exception.advice.CommonNotFoundException;
import com.uniqueauction.web.login.request.LoginRequest;

import lombok.RequiredArgsConstructor;

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
