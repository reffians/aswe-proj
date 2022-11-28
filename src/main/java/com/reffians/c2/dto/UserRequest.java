package com.reffians.c2.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Generated;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;

/** User Request DTO. */
@Generated
@Data
@Validated
public class UserRequest {
  @NotEmpty
  private String username;
  @NotEmpty
  private String password;

  public UsernamePasswordAuthenticationToken getAuthenticationToken() {
    return new UsernamePasswordAuthenticationToken(username, password);
  }
}
