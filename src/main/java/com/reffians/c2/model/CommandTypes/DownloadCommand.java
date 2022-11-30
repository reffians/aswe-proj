package com.reffians.c2.model.CommandTypes;

import javax.persistence.Entity;
import com.reffians.c2.exception.CommandContentMismatchException;
import com.reffians.c2.model.Command;

/** A stop command data model, representing a command created by a user to be
  * executed by a specific beacon.
  */
@Entity
public class DownloadCommand extends Command{

  /** A constructor for the command data model.
    *
    * @param beaconid an integer representing the associated beacon.
    * @param content a user-defined string containing the command content to be
    *     executed by the beacon.
    */
  public DownloadCommand(Integer beaconid, String commandType, String content) throws
      CommandContentMismatchException {
    super(beaconid);
    setType(commandType);
    setCommandContent(content);
  }

  @Override
  public void checkTypeContent(String content) throws CommandContentMismatchException {
    if (content.length() < 1 || content.length() > 10 || !content.matches("^[a-zA-Z0-9[.]]*$")) {
      throw new CommandContentMismatchException("DOWNLOAD", content);
    }
  }
}
