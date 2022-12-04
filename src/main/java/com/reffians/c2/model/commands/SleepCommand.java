package com.reffians.c2.model.commands;

import javax.persistence.Entity;
import com.reffians.c2.exception.CommandContentMismatchException;

import lombok.NoArgsConstructor;

/** A stop command data model, representing a command created by a user to be
  * executed by a specific beacon.
  */
@Entity
@NoArgsConstructor
public class SleepCommand extends Command{

  /** A constructor for the command data model.
    *
    * @param beaconid an integer representing the associated beacon.
    * @param content a user-defined string containing the command content to be
    *     executed by the beacon.
    */
  public SleepCommand(Integer beaconid, String commandType, String content) throws
      CommandContentMismatchException {
    super(beaconid);
    setType(commandType);
    setCommandContent(content);
  }

  @Override
  public void checkTypeContent(String content) throws CommandContentMismatchException {
    try {
      if (Integer.parseInt(content) <= 0) {
        throw new CommandContentMismatchException("SLEEP", content);
      }
    } catch (NumberFormatException e) {
      throw new CommandContentMismatchException("SLEEP", content);
    }
  }
}
