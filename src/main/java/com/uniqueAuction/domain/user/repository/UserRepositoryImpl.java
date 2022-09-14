package com.uniqueAuction.domain.user.repository;

import com.uniqueAuction.domain.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

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
    public User findById(Long userId) {
        return store.get(userId);
    }

    @Override
    public Boolean isDuplicated(String email) {
        return store.entrySet()
                .stream()
                .filter(e -> e.getValue().getEmail().equals(email)).findFirst()
                .map(Map.Entry::getValue).isPresent();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long userId, User updateParam) {
        User findUser = findById(userId);
        findUser.setEmail(updateParam.getEmail());
        findUser.setUsername(updateParam.getUsername());
        findUser.setPhone(updateParam.getPhone());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
