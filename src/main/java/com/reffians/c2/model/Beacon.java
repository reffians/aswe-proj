package com.reffians.c2.model;

import static org.apache.commons.lang3.EnumUtils.isValidEnum;

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("id")
  private Integer id;

  @Getter
  @JsonProperty("userid")
  private Integer userid;
 
  /** Beacon Constructor. **/
  public Beacon(Integer userid) {
    this.id = null;
    this.userid = userid;
  }
}
