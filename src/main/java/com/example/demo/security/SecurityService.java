package com.example.demo.security;

import com.example.demo.Response;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.user.Customer;
import com.example.demo.user.CustomerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.swing.text.html.Option;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class SecurityService {
  private static final String SECURITY_KEY = "my_security";
  
  UserRepository userRepository;
  
  @Autowired
  public SecurityService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  public String createToken(String subject, String userId, String userPw, long expTime) {
    if (expTime <= 0) {
      throw new RuntimeException("만료시간이 0보다 커야함");
    }
    Users user = userRepository.findUserOne(userId, userPw)
      .orElseThrow(() -> new IllegalStateException("No Person"));
    
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    
    byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECURITY_KEY);
    Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
    
    Claims claims = Jwts.claims()
      .setSubject(subject);
    
    claims.put("userId", user.getUserId());
    claims.put("id", user.getId());
    
    return Jwts.builder()
      .setSubject(subject)
      .setClaims(claims)
      .signWith(signatureAlgorithm, signingKey)
      .setExpiration(new Date(System.currentTimeMillis() + expTime))
      .compact();
      
  }
  
  
  public Response getSubject(String token) {
    Claims claims;
    System.out.println(token);
  
    try {
      claims = Jwts.parser()
        .setSigningKey(DatatypeConverter.parseBase64Binary(SECURITY_KEY))
        .parseClaimsJws(token.substring(7))
        .getBody();
    } catch (Exception e) {
      return new Response(500, "Not Valid Token", false, new EmtpyObject());
    }
  
    return new Response(200, "success", true, claims.get("id"));
  
  }
  
  public Response createUser(String userId, String userPw) {
    
    Optional<Users> userByUserId =
      userRepository.findUserByUserId(userId);
    
    if (userByUserId.isPresent()) {
      throw new ApiRequestException("Already user exists");
    }
    
    Users user = new Users(userId, userId, userId);
    userRepository.save(user);
    
    return new Response(200, "User added", true, new EmtpyObject());
  }
}
