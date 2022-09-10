package com.uniqueAuction.domain.user.repository;

import java.util.List;

import com.uniqueAuction.domain.user.entity.User;

public interface UserRepository {
	void save(User user);

	User findById(Long userId);

	List<User> findAll();

	void update(Long userId, User updateParam);

	void clearStore();
}
