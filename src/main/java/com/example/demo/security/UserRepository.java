package com.example.demo.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
  
  @Query("select s from Users s where s.userId = ?1 and s.userPw = ?2")
  Optional<Users> findUserOne(String userId, String userPw);
  
  @Query("Select s from Users s where s.userId = ?1")
  Optional<Users> findUserByUserId(String userId);
}
