package com.reffians.c2.service;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.repository.CommandRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/** Command Service Class. **/
@Service
public class CommandService {
  @Autowired
  private CommandRepository commandRepository;

  /** Get a list of commands by beaconid, updating status from pending to sent.
    *
    * @param beaconid an integer used to identify a beacon.
    * @return list of command objects associated with the specified beaconid.
    */
  public List<Command> getCommands(Integer beaconid) {
    return commandRepository.findByBeaconid(beaconid);
  }

  /** Get a list of commands by beaconid and status, updating status from pending
    * to sent.
    *
    * @param beaconid an integer used to identify a beacon.
    * @param status a status type of the command
    * @return list of command objects associated with the specified beaconid and
    *     status.
    */
  public List<Command> getCommands(Integer beaconid, Status status) {
    return commandRepository.findByBeaconidStatus(beaconid, status.name());
  }

  /** Update the status of commands which have not yet reached newStatus, returning
    * a list of updated commands.
    *
    * @param commands a list of commands to be updated to newStatus should they be
    *     of oldStatus.
    * @param newStatus the status that the commands that have not reached this status
    *     yet will be updated to.
    * @return a list of command objects that have been updated.
    */
  public List<Command> updateCommandStatus(List<Command> commands, Status newStatus) {
    ArrayList<Command> updatedCommands = new ArrayList<Command>();
    for (Command command : commands) {
      if (command.getStatus().compareTo(newStatus) < 0) {
        command.setStatus(newStatus);
        updatedCommands.add(commandRepository.save(command));
      }
    }
    return updatedCommands;
  }

  /** Post to commands table. */
  public Command addCommand(Integer beaconid, String content) {
    return commandRepository.save(new Command(beaconid, content));
  }

}
