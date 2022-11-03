package com.example.demo.user;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Customer {
  
  @Id
  @SequenceGenerator(
    name = "user_sequence",
    sequenceName = "user_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "user_sequence"
  )
  private Long id;
  private String userId;
  private String name;
  
  private String pw;
  @Transient
  private Integer age;
  
  
  public Customer(Long id, String userId, String name,  String pw) {
    this.id = id;
    this.userId = userId;
    this.name = name;
    this.pw = pw;
  }
  
  public Customer(String userId, String name,  String pw) {
    this.userId = userId;
    this.name = name;
    this.pw = pw;
  
  }
  
  public Customer() {
  
  }
  
  public Long getId() {
    return id;
  }
  
  public void setId(String id) {
    userId = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Integer getAge() {
    LocalDate date = LocalDate.of(1994, 9, 1);
    return Period.between(date, LocalDate.now()).getYears();
  }
  
  public void setAge(Integer age) {
    this.age = age;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getPw() {
    return pw;
  }
  
  public void setPw(String pw) {
    this.pw = pw;
  }
  
  public String getUserId() {
    return userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  @Override
  public String toString() {
    return "Customer{" +
      "id=" + id +
      ", userId='" + userId + '\'' +
      ", name='" + name + '\'' +
      ", pw='" + pw + '\'' +
      ", age=" + age +
      '}';
  }
}
