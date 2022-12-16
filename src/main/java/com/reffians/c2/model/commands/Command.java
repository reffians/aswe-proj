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
  private boolean hasBeenSent;

  @Getter
  @JsonProperty("type")
  private String type;

  /**
   * A constructor for the command data model.
   *
   * @param beaconid an integer representing the associated beacon.
   */
  public Command(Integer beaconid) {
    this.id = null;
    this.beaconid = beaconid;
    this.hasBeenSent = false;
  }

  public boolean getHasBeenSent() {
    return hasBeenSent;
  }

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

  /** Sets command content.
    *
    * @param content an String with command content.
    */
  public void setCommandContent(String content) throws CommandContentMismatchException {
    checkTypeContent(content);
    this.content = content;
  }

  public void checkTypeContent(String content) throws CommandContentMismatchException {
  }
}
