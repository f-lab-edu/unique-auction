package com.uniqueAuction.domain.user.repository;

import com.uniqueAuction.data.UserData;
import com.uniqueAuction.domain.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    public void save(User user) {
        /* DB 대신 저장된 전역 데이터 로드 */
        List<User> userData = UserData.instance.userData;
        userData.add(user);
    }
    public Optional<User> findByEmail(String email) {
        /* DB 대신 저장된 전역 데이터 로드 */
        List<User> userData = UserData.instance.userData;

        return Optional.ofNullable(userData.stream()
                .filter(e -> e.getEmail().equals(email)).findFirst()
                .orElseThrow(() -> new RuntimeException("user not exists")));
    }
}
