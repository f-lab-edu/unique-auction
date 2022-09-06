package com.uniqueAuction.domain.user.repository;

import com.uniqueAuction.domain.user.entity.User;
import com.uniqueAuction.exception.LoginException;
import com.uniqueAuction.web.user.dto.LoginRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(User user) {
        user.setUserId(++sequence);
        store.put(user.getUserId(), user);
    }

    @Override
    public User findById(Long userId) {
        return store.get(userId);
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
    public User login(LoginRequest loginRequest) {

        return store.entrySet()
                .stream()
                .filter(user -> user.getValue().getEmail().equals(loginRequest.getEmail())
                        && user.getValue().getPassword().equals(loginRequest.getPassword()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new LoginException("가입하지 않은 이메일이거나 잘못된 비밀번호입니다."));
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
