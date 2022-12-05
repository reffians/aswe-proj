package com.reffians.c2.service;

import com.reffians.c2.exception.CommandContentMismatchException;
import com.reffians.c2.model.commands.Command;
import com.reffians.c2.model.commands.CommandFactory;
import com.reffians.c2.repository.CommandRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Command Service Class. **/
@Service
public class CommandService {
  @Autowired
  private CommandRepository commandRepository;

  /**
   * Get a list of commands by beaconid, updating status from pending to sent.
   *
   * @param beaconid an integer used to identify a beacon.
   * @return list of command objects associated with the specified beaconid.
   */
  public List<Command> getCommands(Integer beaconid) {
    return commandRepository.findByBeaconid(beaconid);
  }

  /**
   * Get a list of commands by beaconid and has_been_sent=false.
   *
   * @param beaconid an integer used to identify a beacon.
   * @return list of command objects associated with the specified beaconid and
   *         status.
   */
  public List<Command> getNotSentCommands(Integer beaconid) {
    return commandRepository.findByBeaconidStatus(beaconid, false);
  }

  /** Post to commands table. */
  public Command addCommand(Integer beaconid, String type, String content)
      throws CommandContentMismatchException, IllegalArgumentException {
    return commandRepository.save(CommandFactory.getCommand(beaconid, type, content));
  }

  /**
   * Find the beaconid of the beacon this command is associated with
   *
   * @param commandid id corresponding to the command.
   * @return a int with the beacon id
   */
  public int getBeaconForCommand(int commandid) {
    return commandRepository.findBeaconForCommand(commandid);
  }

}
