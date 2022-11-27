package com.reffians.c2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.security.SecureRandom;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tomcat.util.codec.binary.Base64;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Data model for the beacon.
 **/
@Entity
@Table(name = "beacons")
@NoArgsConstructor
public class Beacon {
  private static final int TOKEN_LEN = 64;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("username")
  private String username;

  @Getter
  @JsonProperty("token")
  private String token;
 
  /**
   * Constructor for a Beacon. It takes in the username of the user that this beacon belongs to.

   * @param username username of the user that 'owns' this beacon
   **/
  public Beacon(String username) {
    this.id = null;
    this.username = username;
    this.token = generateToken();
  }

  public String generateToken() {
    byte bytes[] = new byte[TOKEN_LEN];
    new SecureRandom().nextBytes(bytes);
    return Base64.encodeBase64URLSafeString(bytes);
  }
}
