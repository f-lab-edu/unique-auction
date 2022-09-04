package com.uniqueAuction.domain.user.service;
import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.domain.user.repository.UserRepository;
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

    /* User 회원조회 */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
