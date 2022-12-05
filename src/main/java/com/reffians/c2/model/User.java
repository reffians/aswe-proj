package com.reffians.c2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/** User table. **/
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {
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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public String getPassword() {
    return getEncodedPassword();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
