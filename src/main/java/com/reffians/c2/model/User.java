package com.reffians.c2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** User table. **/
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @JsonProperty("username")
  public String username;

  @Getter
  @JsonProperty("encodedPassword")
  public String encodedPassword;
}
