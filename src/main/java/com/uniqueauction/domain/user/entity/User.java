package com.uniqueauction.domain.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.uniqueauction.domain.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Enum 타입 일경우
 *  @Enumerated(EnumType.STRING) 선언하지 않으면 enum필드 순서가 들어가는 인트값이 들어간다.
 *  원하는 값을 넣어주기위해 설정을 넣어준다.
 *  유니크키 제약 : 커밋되는 시점에 유니크 조건이 만족하지 않으면 DataIntegrityViolationException 발생
 *  동시성 이슈를 위해 설정해 놓았다.
 */
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

	@Column(unique = true)
	private String email;
	private String username;
	private String phone;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Setter
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Setter
	private String encodedPassword;
}
