package com.uniqueAuction.domain.user.repository;

import java.util.List;

import com.uniqueAuction.domain.user.entity.User;

public interface UserRepository {
	void save(User user);

	Long isExists(String email);

	List<User> findAll();

	void update(Long userId, User updateParam);

}
