package com.uniqueAuction.domain.user.repository;

import com.uniqueAuction.domain.user.entity.Role;
import com.uniqueAuction.domain.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    /* User 데이터 */
    public List<User> userData;

    public UserRepository() {
        /* User 데이터 생성 */
        this.userData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int randomInt = (int) (Math.random() * 90000000) + 10000000;
            String randomStr = Integer.toString(randomInt);
            userData.add(
                    User.builder()
                            .email("lee" + randomStr + "@naver.com")
                            .password("pw")
                            .username("user" + randomStr)
                            .phone("010" + randomStr)
                            .role(Role.CUSTOMER)
                            .build());
        }
    }

    public void save(User user) {
        /* DB 대신 저장된 데이터 로드 */
        List<User> userData = this.userData;
        userData.add(user);
    }
    public Optional<User> findByEmail(String email) {
        /* DB 대신 저장된 데이터 로드 */
        List<User> userData = this.userData;

        return Optional.ofNullable(userData.stream()
                .filter(e -> e.getEmail().equals(email)).findFirst()
                .orElseThrow(() -> new RuntimeException("user not exists")));
    }
}
