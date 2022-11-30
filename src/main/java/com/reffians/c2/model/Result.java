package com.reffians.c2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;  
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
  public Integer commandid;

  @Getter
  @JsonProperty("username")
  public String username;

  @Getter
  @JsonProperty("content")
  public String content;

  @JsonProperty("has_been_read")
  public boolean hasBeenRead;

  @JsonProperty("exec_time")
  public Timestamp exec_time;

  @JsonProperty("result_received_time")
  public Timestamp result_received_time;

  /** A constructor for the result data model.
    *
    * @param command an integer representing the associated comand.
    * @param content a string containing the content of the result received from the beacon.
    * @param exec_time a Timestamp containing the time that the command was executed (by the beacon)
    */
  public Result(Integer commandid, String username, String content) {
    this.id = null;
    this.commandid = commandid;
    this.username = username;
    this.content = content;
    this.result_received_time = new Timestamp(System.currentTimeMillis());
    this.hasBeenRead = false;
  }


  /** Sets result to have been read.
    */
  public void setResultRead() {
    this.hasBeenRead = true;
  }

}
