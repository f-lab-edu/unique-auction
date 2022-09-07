package com.uniqueAuction.domain.user.repository;

import com.uniqueAuction.domain.user.entity.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

    User findById(Long userId);

    List<User> findAll();

    void update(Long userId, User updateParam);

    void clearStore();
}
