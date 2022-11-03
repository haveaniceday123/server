package com.example.demo;

public class Response {
  private int code;
  private String message;
  private boolean isSuccess;
  
  private Object result;
  
  public Response(int code, String message, boolean isSuccess, Object result) {
    this.code = code;
    this.message = message;
    this.isSuccess = isSuccess;
    this.result = result;
  }
  
  public Object getResult() {
    return result;
  }
  
  public void setResult(Object result) {
    this.result = result;
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
