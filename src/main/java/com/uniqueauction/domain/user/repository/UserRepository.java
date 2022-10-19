package com.uniqueauction.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Long getByEmail(String email);
}
