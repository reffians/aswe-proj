package com.reffians.c2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

/** User table. **/
@Entity
@Table(name = "users") //double check what adam named the table for real 
public class User {
  @Id //denotes primary key
  @JsonProperty("username")
  public String username;

  @Getter
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
