package com.example.demo.security;

import com.example.demo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping
public class SecurityController {
  private final SecurityService securityService;
  
  @Autowired
  public SecurityController(SecurityService securityService) {
    this.securityService = securityService;
  }
  
  @PostMapping("api/v1/signUp")
  public Response createUser(@NonNull @RequestBody LoginBody body) {
    String userId = body.getUserId();
    String userPw = body.getUserPw();
    
    return securityService.createUser(userId, userPw);
  }
  
  @PostMapping("api/v1/login")
  public Response createToken(@RequestBody LoginBody body) {
    String userId = body.getUserId();
    String userPw = body.getUserPw();
    String token;
    
    try {
      token =
        securityService.createToken("access_token", userId, userPw, (20 * 1000 * 60));
    } catch (Exception e) {
      return new Response(500, "Not found Id", false, "");
    }
    
    return new Response(200, "success", true, token);
    
  }
  
  @GetMapping("api/v1/tokenChk")
  public Response tokenCheck(@Nullable @RequestHeader(value = "Authorization") String token) {
    
    if (token == null || token.isEmpty()) {
      return new Response(500, "", false, new EmtpyObject());
    }

    return securityService.getSubject(token);

  }
}
