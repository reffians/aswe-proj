package com.reffians.c2.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Entity
@Table(name = "users") //double check what adam named the table for real 
public class User {
  @Id //denotes primary key
  @JsonProperty("username")
  public String username;

  @Getter //do we need this? unsure about what @Getter does
  @JsonProperty("password")
  public String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }
  public User() {

    this.username = null;
    this.password = null;
  }
}
