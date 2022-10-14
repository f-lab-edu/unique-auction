package com.uniqueauction.domain.user.repository;

import java.util.List;

import com.uniqueauction.domain.user.entity.User;

public interface UserRepository {
	void save(User user);

	Long isExists(String email);

	List<User> findAll();

	void update(Long userId, User updateParam);

}
