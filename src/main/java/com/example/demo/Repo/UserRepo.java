package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	int countByUsername(String username);
	User findByUsername(String username);

}
