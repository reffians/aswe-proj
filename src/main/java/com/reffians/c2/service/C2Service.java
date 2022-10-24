package com.reffians.c2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.repository.CommandRepository;
import com.reffians.c2.model.User;
import com.reffians.c2.repository.UserRepository;

@Service
public class C2Service {
  @Autowired
  private CommandRepository commandRepository;
  @Autowired
  private UserRepository userRepository;

  public List<Command> getCommands(Integer beaconid) {
    List<Command> commands = commandRepository.findByBeaconid(beaconid);
    this.updateCommandStatus(commands, Status.pending, Status.sent);
    return commands;
  }

  public List<Command> getCommands(Integer beaconid, Status status) {
    List<Command> commands = commandRepository.findByBeaconidStatus(beaconid, status.name());
    this.updateCommandStatus(commands, Status.pending, Status.sent);
    return commands;
  }
  // submit command batch methods
  /* 
  public List<Command> checkCommand(Integer beaconid, Integer commandid) {
	List<Command> thiscommand = commandRepository.findCommandByid(beaconid, commandid);
	return thiscommand;
  } */
  /*
  public void updateCommand(Integer beaconid, Integer commandid) {
	commandRepository.updateCommand(Status.executed, beaconid, commandid );
  } */
  // registration and login methods

  public List<User> getUsers(String username) {
	List<User> users = userRepository.findByUsername(username);
	return users;
  }

  public List<User> getUsers(String username, String password) {
	List<User> users = userRepository.findByUnamePword(username, password);
	return users;
  }

  public void addUser(String username, String password){
	userRepository.insertUser(username, password);
  }

  private void updateCommandStatus(List<Command> commands, Status oldStatus, Status newStatus) {
    for (Command command : commands) {
      if (command.getStatus() == oldStatus) {
        command.setStatus(newStatus);
        commandRepository.save(command);
      }
    }
  }
  // bcrypt
}