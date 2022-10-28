package com.reffians.c2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Data model for the beacon.
 **/
@Entity
@Table(name = "beacons")
@NoArgsConstructor
public class Beacon {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("id")
  private Integer id;

  /**
   * username of the user that 'owns' this beacon.
   **/
  @Getter
  @JsonProperty("username")
  private String username;
 
  /**
   * Constructor for a Beacon. It takes in the username of the user that this beacon belongs to.

   * @param username username of the user that 'owns' this beacon
   **/
  public Beacon(String username) {
    this.id = null;
    this.username = username;
  }
}
