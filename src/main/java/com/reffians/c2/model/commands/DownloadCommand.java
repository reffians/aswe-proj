package com.reffians.c2.model.commands;

import com.reffians.c2.exception.CommandContentMismatchException;
import java.net.URL;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;


/** A stop command data model, representing a command created by a user to be
  * executed by a specific beacon.
  */
@Entity
@NoArgsConstructor
public class DownloadCommand extends Command {

  /** A constructor for the command data model.
   *
   * @param beaconid an integer representing the associated beacon.
   * @param content a user-defined string containing the command content to be
   *     executed by the beacon.
   */
  public DownloadCommand(Integer beaconid, String content) throws
      CommandContentMismatchException {
    super(beaconid);
    setType("DOWNLOAD");
    setCommandContent(content);
  }

  @Override
  public void checkTypeContent(String content) throws CommandContentMismatchException {
    try {
      URL url = new URL(content);
    } catch (Exception e) {
      throw new CommandContentMismatchException("DOWNLOAD", content);
    }
  }
}
