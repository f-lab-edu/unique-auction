package com.uniqueauction.domain.user.service;

import static com.uniqueauction.exception.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.domain.user.repository.UserRepository;
import com.uniqueauction.exception.advice.CommonException;
import com.uniqueauction.exception.advice.CommonNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;

	@Transactional
	public User join(User user) {
		if (!existsByEmail(user.getEmail())) {
			userRepository.save(user);
		} else {
			throw new CommonException(DUPLICATE_USER);
		}
		return userRepository.findByEmail(user.getEmail());
	}

	@Transactional
	public User update(User updateUser) {
		User user = userRepository.findByEmail(updateUser.getEmail());
		user.update(updateUser);
		return user;
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
