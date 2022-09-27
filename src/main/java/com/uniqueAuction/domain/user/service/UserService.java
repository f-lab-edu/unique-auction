package com.uniqueAuction.domain.user.service;

import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.user.repository.UserRepository;
import com.uniqueAuction.exception.advice.user.UserException;
import com.uniqueAuction.web.user.request.JoinRequest;
import com.uniqueAuction.web.user.request.UpdateUserRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EncryptService encryptService;

	public void join(JoinRequest joinRequest) {
		User user = joinRequest.toEntity(joinRequest);
		Long findId = isExists(user);

		if (findId == 0) {
			user.setEncodedPassword(encryptService.encrypt(joinRequest.getPassword()));
		} else {
			throw new UserException("이미 존재하는 이메일입니다.");
		}

		userRepository.save(user);
		
	}

	public void update(UpdateUserRequest updateUserRequest) {
		User user = updateUserRequest.toEntity(updateUserRequest);
		Long findId = isExists(user);

		if (findId > 0) {
			userRepository.update(findId, user);
		} else {
			throw new UserException("존재하지 않는 유저입니다.");
		}

	}

	private Long isExists(User user) {
		return userRepository.isExists(user.getEmail());
	}

}
