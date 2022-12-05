package com.reffians.c2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;  
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
@Table(name = "results")
@NoArgsConstructor
public class Result {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty("id")
  public Integer id;

  @Getter
  @JsonProperty("commandid")
  public int commandid;

  @Getter
  @JsonProperty("username")
  public String username;

  @Getter
  @JsonProperty("content")
  public String content;

  @Getter
  @JsonProperty("has_been_read")
  public boolean hasBeenRead;

  /** A constructor for the result data model.

   * @param commandid an integer representing the associated comand.
   * @param content a string containing the content of the result received from the beacon.
  **/
  public Result(int commandid, String username, String content) {
    this.id = null;
    this.commandid = commandid;
    this.username = username;
    this.content = content;
    this.hasBeenRead = false;
  }

  public boolean getHasBeenRead() {
    return hasBeenRead;
  }

  /** Sets result to have been read.
    */
  public void setResultRead() {
    this.hasBeenRead = true;
  }

}
