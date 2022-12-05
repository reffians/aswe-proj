package com.reffians.c2.model.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reffians.c2.exception.CommandContentMismatchException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A command data model, representing a command created by a user to be
 * executed by a specific beacon.
 */
@Entity
@Table(name = "commands")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
public class Command {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("id")
  private Integer id;

  @Getter
  @JsonProperty("beaconid")
  private Integer beaconid;

  @Getter
  @JsonProperty("content")
  private String content;

  @JsonProperty("has_been_sent")
  public boolean hasBeenSent;

  @JsonProperty("type")
  public String type;

  @JsonProperty("received_time")
  public Timestamp received_time;

  @JsonProperty("time_sent")
  public Timestamp time_sent;

  /** The enum of valid command status. **/
  // public static enum Status {
  // pending,
  // sent,
  // executed,
  // finished;

  // /** Checks whether the provided string represents a valid command status.
  // *
  // * @param status the status string under question.
  // * @return a boolean indicating whether the provided string represents a valid
  // * command status.
  // */
  // public static boolean isValid(String status) {
  // return isValidEnum(Status.class, status);
  // }
  // }

  /**
   * A constructor for the command data model.
   *
   * @param beaconid an integer representing the associated beacon.
   * @param content  a user-defined string containing the command content to be
   *                 executed by the beacon.
   */
  public Command(Integer beaconid) {
    this.id = null;
    this.beaconid = beaconid;
    this.hasBeenSent = false;
  }

  // /** Retrieves command status.
  // *
  // * @return an enum representing a valid status.
  // */
  // public Status getStatus() {
  // return Status.valueOf(this.status);
  // }

  /**
   * Sets command to have been sent.
   */
  public void setCommandSent() {
    this.hasBeenSent = true;
  }

  /**
   * Sets command type.
   *
   * @param type an String representing a type of command.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Sets command content.
   *
   * @param type an String with command content.
   */
  public void setCommandContent(String content) throws CommandContentMismatchException {
    checkTypeContent(content);
    this.content = content;
  }

  public void checkTypeContent(String content) throws CommandContentMismatchException {
  };
}
