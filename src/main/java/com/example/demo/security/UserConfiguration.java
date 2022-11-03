package com.example.demo.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
  
  @Bean
  CommandLineRunner commandLineRunners(
    UserRepository userRepository
  ) {
    return args -> {
      Users user = new Users(
        "admin",
        "admin",
        "admin"
      );
      
      userRepository.save(user);
    };
  }
  
}
