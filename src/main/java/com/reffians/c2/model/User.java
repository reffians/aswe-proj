package com.reffians.c2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

/** User table. **/
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
  @Id
  @Getter
  @JsonProperty("username")
  public String username;

  @Getter
  @JsonProperty("encodedPassword")
  public String encodedPassword;

  public User(String username, String rawPassword, PasswordEncoder passwordEncoder) {
    this.username = username;
    this.encodedPassword = passwordEncoder.encode(rawPassword);
  }
}
