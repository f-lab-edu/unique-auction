package com.uniqueAuction.data;

import com.uniqueAuction.domain.user.entity.Role;
import com.uniqueAuction.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserData {
    public List<User> userData;
    /* singleton */
    public final static UserData instance = new UserData();

    private UserData() {
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

}
