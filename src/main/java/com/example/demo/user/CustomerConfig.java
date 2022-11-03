package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {
  
  @Bean
  CommandLineRunner commandLineRunner(
    CustomerRepository customerRepository) {
      return args -> {
        Customer admin = new Customer(
          "admin",
          "admin",
          "admin"
        );
        Customer user1 = new Customer(
          "user1",
          "user1",
          "user1"
        );
        
        customerRepository.saveAll(
          List.of(admin, user1)
        );
        
      };
  };
  
}
