package com.example.demo.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
  
  @Id
  @SequenceGenerator(
    name = "seq",
    sequenceName = "seq",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "seq"
  )
  private Long id;
  
  private String userId;
  
  private String userName;
  
  private String userPw;
  
  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", userId='" + userId + '\'' +
      ", userName='" + userName + '\'' +
      ", userPw='" + userPw + '\'' +
      '}';
  }
  
  public Users(String userId, String userName, String userPw) {
    this.userId = userId;
    this.userName = userName;
    this.userPw = userPw;
  }
}
