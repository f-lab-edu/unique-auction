package com.uniqueAuction.domain.user.repository;

import com.uniqueAuction.domain.user.entity.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

	Long isExists(String email);

    List<User> findAll();

    void update(Long userId, User updateParam);

}
