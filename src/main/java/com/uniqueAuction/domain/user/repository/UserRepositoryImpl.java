package com.uniqueAuction.domain.user.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.uniqueAuction.domain.user.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	private static final ConcurrentHashMap<Long, User> store = new ConcurrentHashMap<>();
	private static final AtomicLong sequence = new AtomicLong();

	@Override
	public void save(User user) {
		user.setId(sequence.addAndGet(1));
		store.put(user.getId(), user);
	}

	@Override
	public Long isExists(String email) {
		return store.entrySet()
			.stream()
			.filter(e -> e.getValue().getEmail().equals(email)).findFirst()
			.map(e -> e.getValue().getId())
			.orElse(0L);
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<>(store.values());
	}

	@Override
	public void update(Long userId, User updateParam) {
		store.put(userId, updateParam);
	}
}
