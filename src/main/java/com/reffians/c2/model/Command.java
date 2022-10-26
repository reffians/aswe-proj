package com.reffians.c2.model;

import static org.apache.commons.lang3.EnumUtils.isValidEnum;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

/** A command data model, representing a command created by a user to be
 * executed by a specific beacon. **/
@Entity
@Table(name = "command")
public class Command {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
 
  /** The enum of valid command status. **/
  public static enum Status {
    pending,
    sent,
    executed,
    finished;
  }

  /** A constructor for the command data model.

   * @param beaconid an integer representing the associated beacon.

   * @param content a user-defined string containing the command content to be
    executed by the beacon. **/
  public Command(Integer beaconid, String content) {
    //this.id = 0; // change to auto gen
    this.beaconid = beaconid;
    this.content = content;
    this.status = Status.pending.name();
  }

  /** Checks whether the provided string represents a valid command status.

   * @param status the status string under question.

   * @return a boolean indicating whether the provided string represents a valid
    command status.
   */
  public static boolean isValidStatus(String status) {
    return isValidEnum(Status.class, status);
  }

  /** Retrieves command status.

   * @return an enum representing a valid status.
  */
  public Status getStatus() {
    return Status.valueOf(this.status);
  }

  /** Sets command status.

   * @param status an enum representing a valid status.
   **/
  public void setStatus(Status status) {
    this.status = status.name();
  }
}
