package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository
  extends JpaRepository<Customer, Long> {
  
  @Query("Select s from Customer s Where s.userId = ?1")
  Optional<Customer> findCustomerById(String userId);
  
}
