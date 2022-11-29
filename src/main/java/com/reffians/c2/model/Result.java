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
  @JsonProperty("content")
  public String content;

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
  public Result(Integer commandid, String content, Timestamp exec_time) {
    this.id = null;
    this.commandid = commandid;
    this.content = content;
    this.exec_time = exec_time;
    this.result_received_time = new Timestamp(System.currentTimeMillis());
  }
}
