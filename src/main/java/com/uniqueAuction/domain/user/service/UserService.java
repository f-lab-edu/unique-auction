package com.uniqueAuction.domain.user.service;

import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.user.repository.UserRepository;
import com.uniqueAuction.exception.advice.user.UserException;
import com.uniqueAuction.web.user.request.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EncryptService encryptService;

    public void join(JoinRequest joinRequest) {
        User user = joinRequest.toEntity(joinRequest);
        if (!isDuplicated(user)) {
            user.setEncodedPassword(encryptService.encrypt(joinRequest.getPassword()));
        } else {
            throw new UserException("이미 존재하는 이메일입니다.");
        }
        userRepository.save(user);
    }

    private boolean isDuplicated(User user) {
        return userRepository.isDuplicated(user.getEmail());
    }

}
