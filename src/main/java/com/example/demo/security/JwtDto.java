package com.example.demo.security;

import org.springframework.http.HttpStatus;

public class JwtDto {
  private int code;
  private String message;
  private boolean isSuccess;
  
  public JwtDto(int code, String message, boolean isSuccess) {
    this.code = code;
    this.message = message;
    this.isSuccess = isSuccess;
  }
  
  public int getCode() {
    return code;
  }
  
  public void setCode(int code) {
    this.code = code;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public boolean getIsSuccess() {
    return isSuccess;
  }
  
  public void setIsSuccess(boolean isSuccess) {
    this.isSuccess = isSuccess;
  }
}
