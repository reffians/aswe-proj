package com.reffians.c2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

/** Command Data Model. **/
@Entity
@Table(name = "beacons")
public class Beacon {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("id")
  private Integer id;

  @Getter
  @JsonProperty("username")
  private String username;
 
  /** Beacon Constructor. **/
  public Beacon(String username) {
    this.id = null;
    this.username = username;
  }
}
