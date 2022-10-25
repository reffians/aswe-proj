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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("id")
  private Integer id;

  @Getter
  @JsonProperty("beaconid")
  private Integer beaconid;

  @Getter
  @JsonProperty("content")
  private String content;

  @JsonProperty("status")
  private String status;
 
  /** Valid Command Status. **/
  public static enum Status {
    pending,
    sent,
    executed,
    finished;
  }

  /** Command Constructor. **/
  public Command(Integer beaconid, String content) {
    this.id = null;
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
