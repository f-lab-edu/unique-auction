package com.uniqueAuction.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.user.repository.UserRepository;
import com.uniqueAuction.web.user.request.JoinRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public void join(JoinRequest joinRequest) {
		User user = joinRequest.toEntity(joinRequest);
		user.setEncodedPassword(passwordEncoder.encode(joinRequest.getPassword()));
		userRepository.save(user);
	}

}
