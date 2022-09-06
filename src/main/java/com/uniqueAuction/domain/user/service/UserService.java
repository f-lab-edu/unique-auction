package com.uniqueAuction.domain.user.service;

import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.user.repository.UserRepository;
import com.uniqueAuction.web.user.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /* User 회원가입 */
    public void join(User user) {
        userRepository.save(user);
    }

    /* User Id회원조회 */
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    /* User login */
    public User login(LoginRequest loginRequest) {
        return userRepository.login(loginRequest);
    }
}
