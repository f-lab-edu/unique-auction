package com.uniqueauction.domain.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	void userJoinTest() {
	/*
        // 신규 회원 가입
        User user = User.builder()
                .email("new" + "@naver.com")
                .password("NewPw")
                .username("newUser")
                .phone("new01012345678")
                .role(Role.CUSTOMER)
                .build();

        // 회원가입 Service
        userService.join(user);

        // 회원가입 후 회원조회를 통해 등록처리 되었는지 확인
        User findUser = userService.findByEmail(user.getEmail());
        Assertions.assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
        */
	}
}