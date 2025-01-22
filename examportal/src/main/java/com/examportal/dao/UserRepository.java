package com.examportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.beans.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

}
