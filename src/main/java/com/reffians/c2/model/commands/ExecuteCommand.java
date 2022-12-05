package com.reffians.c2.model.commands;

import com.reffians.c2.exception.CommandContentMismatchException;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

/** An execute command data model, representing a command created by a user to be
  * executed by a specific beacon.
  */
@Entity
@NoArgsConstructor
public class ExecuteCommand extends Command {

  /** A constructor for the command data model.
    *
    * @param beaconid an integer representing the associated beacon.
    * @param content a user-defined string containing the command content to be
    *     executed by the beacon.
    */
  public ExecuteCommand(Integer beaconid, String content) throws
      CommandContentMismatchException {
    super(beaconid);
    setType("EXECUTE");
    setCommandContent(content);
  }

  @Override
  public void checkTypeContent(String content) throws CommandContentMismatchException {
    if (!content.matches("^[a-zA-Z0-9[-][.][/]]*$")) {
      throw new CommandContentMismatchException("EXECUTE", content);
    }
  }
}
