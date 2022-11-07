package com.uniqueauction.domain.user.service;

import static com.uniqueauction.exception.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonException;
import com.uniqueauction.exception.advice.CommonNotFoundException;
import com.uniqueauction.web.user.request.JoinRequest;
import com.uniqueauction.web.user.request.UpdateUserRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final EncryptService encryptService;

	@Transactional
	public void join(JoinRequest joinRequest) {
		User user = joinRequest.convert(joinRequest);
		if (!existsByEmail(user.getEmail())) {
			user.setEncodedPassword(encryptService.encrypt(joinRequest.getPassword()));
		} else {
			throw new CommonException(DUPLICATE_USER);
		}
		userRepository.save(user);
	}

	@Transactional
	public User update(UpdateUserRequest userRequest) {
		User updateUser = userRequest.toEntity();
		if (!existsByEmail(updateUser.getEmail())) {
			updateUser = userRequest.toEntity();
		} else {
			throw new CommonException(NOT_FOUND_USER);
		}
		return userRepository.findById(userRepository.save(updateUser).getId()).get();
	}

	@Transactional(readOnly = true)
	public User findById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new CommonNotFoundException(NOT_FOUND_USER));
	}

	private boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
}
