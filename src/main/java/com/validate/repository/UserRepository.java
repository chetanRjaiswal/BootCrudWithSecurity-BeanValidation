package com.validate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.validate.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(int id);
	Optional<User> findByEmail(String email);
	
}
