package com.uniqueauction.domain.user.service;

import static com.uniqueauction.exception.ErrorCode.*;

import org.springframework.stereotype.Service;

import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonException;
import com.uniqueauction.exception.advice.CommonNotFoundException;
import com.uniqueauction.web.user.request.JoinRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final EncryptService encryptService;

	public void join(JoinRequest joinRequest) {
		User user = joinRequest.convert(joinRequest);
		if (existsByEmail(user.getEmail())) {
			user.setEncodedPassword(encryptService.encrypt(joinRequest.getPassword()));
		} else {
			throw new CommonException(DUPLICATE_USER);
		}

		userRepository.save(user);
	}

	public void update(Long id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new CommonNotFoundException(NOT_FOUND_USER));
		userRepository.save(user);
	}

	private boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
}
