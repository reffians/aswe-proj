package com.reffians.c2.model;

import java.util.List;

/**
 * A commandList data model, representing a list of commands created by a user to be
 * executed by a specific beacon. Acts as wrapper class to correctly parse json batch 
 * to arraylist.
*/
public class CommandList {
  private List<Command> commands;

  public List<Command> getCommands() {
    return this.commands;
  }

  public void setCommands(List<Command> commands) {
    this.commands = commands;
  }   
}