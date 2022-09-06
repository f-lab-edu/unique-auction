package com.uniqueAuction.domain.user.repository;

import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.web.user.dto.LoginRequest;

import java.util.List;

public interface UserRepository {
    void save(User user);

    User findById(Long userId);

    User login(LoginRequest loginRequest);

    List<User> findAll();

    void update(Long userId, User updateParam);

    void clearStore();
}
