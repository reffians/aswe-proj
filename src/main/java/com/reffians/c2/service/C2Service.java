package com.reffians.c2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.repository.CommandRepository;

@Service
public class C2Service {
  @Autowired
  private CommandRepository commandRepository;
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

  public List<User> getUsers(String username) {
	List<User> users = userRepository.findByUsername(username);
  }

  public List<User> getUsers(String username, String password) {
	List<User> users = userRepository.findByUnamePword(username, password);
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