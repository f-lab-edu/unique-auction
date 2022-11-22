package com.uniqueauction.domain.user.repository;

import static com.uniqueauction.domain.user.entity.Role.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import javax.transaction.Transactional;

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

	@Test
	@DisplayName("유저 저장 테스트")
	void userSave() {

		setUp();
		//then
		assertThat(user.getEmail()).isEqualTo("test@test.com");
		assertThat(user.getUsername()).isEqualTo("test");
		assertThat(user.getPhone()).isEqualTo("010-1234-1234");
		assertThat(user.getEncodedPassword()).isEqualTo("#############");

		clear();
	}

	@Test
	@DisplayName("유저 업데이트 테스트")
	@Transactional
	void userUpdate() {

		setUp();

		User byEmailUser = userRepository.findByEmail(getUpdateUser().getEmail());

		byEmailUser.update(getUpdateReq().toEntity());

		User updateUser = userRepository.findByEmail(getUpdateUser().getEmail());

		//then
		assertThat(updateUser.getEmail()).isEqualTo("test@test.com");
		assertThat(updateUser.getUsername()).isEqualTo("update");
		assertThat(updateUser.getPhone()).isEqualTo("010-1234-56678");
		assertThat(updateUser.getEncodedPassword()).isEqualTo("@@@@@@@@@@@");

		clear();
	}

	@Test
	@DisplayName("중복메일 확인 테스트")
	@Transactional
	void existsByEmailTest() {
		setUp();

		boolean existsByEmail = userRepository.existsByEmail(getUser().getEmail());

		assertThat(existsByEmail).isEqualTo(true);

		clear();
	}

	@Test
	@DisplayName("이메일로 조회  테스트")
	@Transactional
	void selectByEmailTest() {

		setUp();

		User byEmail = userRepository.findByEmail(getUser().getEmail());

		assertThat(byEmail.getEmail()).isEqualTo(getUser().getEmail());

		clear();
	}

	@Test
	@DisplayName("이메일과 암호화된 비밀번호로 조회")
	void selectEmailAndEncodePassword() {

		setUp();

		Optional<User> user = userRepository.findByEmailAndEncodedPassword(getUser().getEmail(),
			getUpdateUser().getEncodedPassword());

		user.ifPresent(
			u ->
				assertThat(u.getEmail()).isEqualTo(getUser().getEmail())
		);

		clear();

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

	void setUp() {
		user = userRepository.save(getUser());
	}

	void clear() {
		userRepository.delete(user);
	}

}
