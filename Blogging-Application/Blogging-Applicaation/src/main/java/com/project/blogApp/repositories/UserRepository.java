package com.project.blogApp.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.blogApp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

//	@Query("select u from User u where email = :email")
//	public Optional<User> findByEmail(@Param("email") String email);

	public Optional<User> findByEmail(String email);
}
