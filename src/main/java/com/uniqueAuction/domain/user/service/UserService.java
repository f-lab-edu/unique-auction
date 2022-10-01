package com.uniqueAuction.domain.user.service;

import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.user.repository.UserRepository;
import com.uniqueAuction.exception.advice.CommonException;
import com.uniqueAuction.exception.advice.CommonNotFoundException;
import com.uniqueAuction.web.user.request.JoinRequest;
import com.uniqueAuction.web.user.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.uniqueAuction.exception.ErrorCode.DUPLICATE_USER;
import static com.uniqueAuction.exception.ErrorCode.NOT_FOUND_USER;

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
            throw new CommonException(DUPLICATE_USER);
        }

        userRepository.save(user);
    }

    public void update(UpdateUserRequest updateUserRequest) {
        User user = updateUserRequest.toEntity(updateUserRequest);
        Long findId = isExists(user);

        if (findId > 0) {
            userRepository.update(findId, user);
        } else {
            throw new CommonNotFoundException(NOT_FOUND_USER);
        }
    }

    private Long isExists(User user) {
        return userRepository.isExists(user.getEmail());
    }

}
