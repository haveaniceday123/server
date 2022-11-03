package com.example.demo.security;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginBody {

  private String userId;
  private String userPw;


}
