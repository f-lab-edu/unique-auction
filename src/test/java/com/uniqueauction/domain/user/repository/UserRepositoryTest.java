package com.uniqueauction.domain.user.repository;

import static com.uniqueauction.domain.user.entity.Role.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uniqueauction.TestContainerBase;
import com.uniqueauction.domain.user.entity.User;
import com.uniqueauction.web.user.request.UpdateUserRequest;

@TestContainerBase
@Rollback(value = false)
@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	User user;

	@BeforeEach
	void set() {
		System.out.println("setUp");
		user = userRepository.save(getUser());
	}

	@AfterEach
	void clear() {
		System.out.println("clear");
		userRepository.deleteAll();
	}

	@Test
	@DisplayName("유저 저장 테스트")
	void userSave() {
		//then
		assertThat(user.getEmail()).isEqualTo("test@test.com");
		assertThat(user.getUsername()).isEqualTo("test");
		assertThat(user.getPhone()).isEqualTo("010-1234-1234");
		assertThat(user.getEncodedPassword()).isEqualTo("#############");
	}

	@Test
	@DisplayName("유저 업데이트 테스트")
	@Transactional
	void userUpdate() {
		User byEmailUser = userRepository.findByEmail(getUpdateUser().getEmail());

		byEmailUser.update(getUpdateReq());

		User updateUser = userRepository.findByEmail(getUpdateUser().getEmail());

		//then
		assertThat(updateUser.getEmail()).isEqualTo("test@test.com");
		assertThat(updateUser.getUsername()).isEqualTo("update");
		assertThat(updateUser.getPhone()).isEqualTo("010-1234-56678");
		assertThat(updateUser.getEncodedPassword()).isEqualTo("@@@@@@@@@@@");
	}

	@Test
	@DisplayName("중복메일 확인 테스트")
	@Transactional
	void existsByEmailTest() {
		boolean existsByEmail = userRepository.existsByEmail(getUser().getEmail());

		assertThat(existsByEmail).isEqualTo(true);
	}

	@Test
	@DisplayName("이메일로 조회  테스트")
	@Transactional
	void selectByEmailTest() {
		User byEmail = userRepository.findByEmail(getUser().getEmail());

		assertThat(byEmail.getEmail()).isEqualTo(getUser().getEmail());
	}

	@Test
	@DisplayName("이메일과 암호화된 비밀번호로 조회")
	void selectEmailAndEncodePassword() {

		Optional<User> user = userRepository.findByEmailAndEncodedPassword(getUser().getEmail(),
			getUpdateUser().getEncodedPassword());

		user.ifPresent(
			u ->
				assertThat(u.getEmail()).isEqualTo(getUser().getEmail())
		);

	}

	private User getUser() {
		return User.builder()
			.email("test@test.com")
			.username("test")
			.encodedPassword("#############")
			.phone("010-1234-1234")
			.role(CUSTOMER)
			.build();
	}

	private User getUpdateUser() {
		return User.builder()
			.email("test@test.com")
			.username("update")
			.encodedPassword("@@@@@@@@@@@")
			.phone("010-1234-56678")
			.role(ADMIN)
			.build();
	}

	public UpdateUserRequest getUpdateReq() {
		return UpdateUserRequest.builder()
			.email("test@test.com")
			.username("update")
			.password("@@@@@@@@@@@")
			.phone("010-1234-56678")
			.build();
	}

}
