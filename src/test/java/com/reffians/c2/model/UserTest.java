package com.reffians.c2.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootTest
class UserTest {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Test 
  void testUserConstructor(){
    String username = "username";
    String rawPassword = "password";
    User user = new User(username, rawPassword, passwordEncoder);
    assertEquals(username, user.getUsername());
    // assertEquals(passwordEncoder.encode(rawPassword), user.getEncodedPassword());
  }

  @Test 
  void testUserExtras(){
    String username = "username";
    String rawPassword = "password";
    User user = new User(username, rawPassword, passwordEncoder);
    assertEquals(true, user.isAccountNonExpired());
    assertEquals(true, user.isAccountNonLocked());
    assertEquals(true, user.isCredentialsNonExpired());
    assertEquals(true, user.isEnabled());
  }
}
