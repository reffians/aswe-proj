package com.reffians.c2.model.commands;

import com.reffians.c2.exception.CommandContentMismatchException;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

/** A stop command data model, representing a command created by a user to be
  * executed by a specific beacon.
  */
@Entity
@NoArgsConstructor
public class GetHostNameCommand extends Command {

  /** A constructor for the command data model.
    *
    * @param beaconid an integer representing the associated beacon.
    * @param content a user-defined string containing the command content to be
    *     executed by the beacon.
    */
  public GetHostNameCommand(Integer beaconid, String content) throws 
      CommandContentMismatchException {
    super(beaconid);
    setType("GETHOSTNAME");
    setCommandContent(content);
  }

  @Override
  public void checkTypeContent(String content) throws CommandContentMismatchException {
    if (!content.isEmpty()) {
      throw new CommandContentMismatchException("GETHOSTNAME", content);
    }
  }
}
