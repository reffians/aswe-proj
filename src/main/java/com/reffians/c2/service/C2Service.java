package com.reffians.c2.service;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.model.User;
import com.reffians.c2.repository.CommandRepository;
import com.reffians.c2.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**C2 Service Class. **/
@Service
public class C2Service {
  @Autowired
  private CommandRepository commandRepository;
  @Autowired
  private UserRepository userRepository;

  /** Get a list of commands by beaconid, updating status from pending to sent. */
  public List<Command> getCommands(Integer beaconid) {
    List<Command> commands = commandRepository.findByBeaconid(beaconid);
    this.updateCommandStatus(commands, Status.pending, Status.sent);
    return commands;
  }

  /** Get a list of commands by beaconid and status, updating status from pending to sent. */
  public List<Command> getCommands(Integer beaconid, Status status) {
    List<Command> commands = commandRepository.findByBeaconidStatus(beaconid, status.name());
    this.updateCommandStatus(commands, Status.pending, Status.sent);
    return commands;
  }

  // registration and login methods
  public List<User> getUsers(String username) {
    return userRepository.findByUsername(username);
  }

  public List<User> getUsers(String username, String password) {
    return userRepository.findByUnamePword(username, password);
  }

  public void addUser(String username, String password){
    userRepository.insertUser(username, password);
  }

  /** Update the status of a list of commands, returns updated commands. **/
  public List<Command> updateCommandStatus(List<Command> commands, Status oldStatus,
      Status newStatus) {
    ArrayList<Command> updatedCommands = new ArrayList<Command>();
    for (Command command : commands) {
      if (command.getStatus() == oldStatus) {
        command.setStatus(newStatus);
        updatedCommands.add(commandRepository.save(command));
      }
    }
    return updatedCommands;
  }
}