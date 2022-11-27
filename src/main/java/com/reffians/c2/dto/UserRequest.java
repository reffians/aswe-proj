package com.reffians.c2.dto;

import lombok.Data;
import lombok.Generated;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/** User Request DTO. */
@Generated
@Data
public class UserRequest {
  private String username;
  private String password;

  public UsernamePasswordAuthenticationToken getAuthenticationToken() {
    return new UsernamePasswordAuthenticationToken(username, password);
  }
}
