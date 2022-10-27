package com.reffians.c2.model;

import static org.apache.commons.lang3.EnumUtils.isValidEnum;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** A command data model, representing a command created by a user to be
  * executed by a specific beacon.
  */
@Entity
@Table(name = "command")
@NoArgsConstructor
public class Command {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("id")
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

    /** Checks whether the provided string represents a valid command status.
      *
      * @param status the status string under question.
      * @return a boolean indicating whether the provided string represents a valid
      *     command status.
      */
    public static boolean isValid(String status) {
      return isValidEnum(Status.class, status);
    }
  }

  /** A constructor for the command data model.
    *
    * @param beaconid an integer representing the associated beacon.
    * @param content a user-defined string containing the command content to be
    *     executed by the beacon.
    */
  public Command(Integer beaconid, String content) {
    this.id = null;
    this.beaconid = beaconid;
    this.content = content;
    this.status = Status.pending.name();
  }

  /** Retrieves command status.
    *
    * @return an enum representing a valid status.
    */
  public Status getStatus() {
    return Status.valueOf(this.status);
  }

  /** Sets command status.
    *
    * @param status an enum representing a valid status.
    */
  public void setStatus(Status status) {
    this.status = status.name();
  }
}
