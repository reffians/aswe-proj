package com.reffians.c2.service;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.repository.CommandRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**C2 Service Class. **/
@Service
public class C2Service {
  @Autowired
  private CommandRepository commandRepository;

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

  private void updateCommandStatus(List<Command> commands, Status oldStatus, Status newStatus) {
    for (Command command : commands) {
      if (command.getStatus() == oldStatus) {
        command.setStatus(newStatus);
        commandRepository.save(command);
      }
    }
  }
}