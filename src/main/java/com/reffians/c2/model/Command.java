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
@Table(name = "command")
public class Command {
  
  @Id
  @JsonProperty("id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  @Getter
  @JsonProperty("beaconid")
  public Integer beaconid;

  @Getter
  @JsonProperty("content")
  public String content;

  @JsonProperty("status")
  public String status;
 
  /** Valid Command Status. **/
  public static enum Status {
    pending,
    sent,
    executed,
    finished;
  }

  /** Command Constructor. **/
  public Command(Integer beaconid, String content) {
    this.beaconid = beaconid;
    this.content = content;
    this.status = Status.pending.name();
  }

  public static boolean isValidStatus(String status) {
    return isValidEnum(Status.class, status);
  }

  public Status getStatus() {
    return Status.valueOf(this.status);
  }

  public void setStatus(Status status) {
    this.status = status.name();
  }
}
