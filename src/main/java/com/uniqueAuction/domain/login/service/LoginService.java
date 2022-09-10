package com.uniqueAuction.domain.login.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.user.repository.UserRepository;
import com.uniqueAuction.exception.advice.login.LoginException;
import com.uniqueAuction.web.user.request.LoginRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService {

	private final UserRepository userRepository;

	public User login(LoginRequest loginRequest) {

		List<User> users = userRepository.findAll();

		return users.stream()
			.filter(user -> user.getEmail().equals(loginRequest.getEmail())
				&& user.getEncodedPassword().equals(loginRequest.getPassword()))
			.findFirst()
			.orElseThrow(() -> new LoginException("가입하지 않은 이메일이거나 잘못된 비밀번호입니다."));
	}
}
