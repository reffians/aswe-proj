package com.reffians.c2.model.CommandTypes;

import com.reffians.c2.model.Command;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * Class to create the necessary Command classes (as part of a factory pattern).
 */
public class CommandFactory {
  /** Initializes a command class given the command type (i.e. STOP, SLEEP, UPLOAD)
    *
    * @param commandType String indicating from which alert service this JSON payload came from.
    * @param 
    * @return the correct Command object.
    */
  public static Command getCommand(@NotNull Integer beaconid, @NotEmpty String commandType,
      String content) {
    switch (commandType) {
      case "STOP":
        return new StopCommand(beaconid, commandType, content);
      case "SLEEP":
        return new SleepCommand(beaconid, commandType, content);
      case "UPLOAD":
        return new UploadCommand(beaconid, commandType, content);
      case "DOWNLOAD":
        return new DownloadCommand(beaconid, commandType, content);
      case "GETHOSTNAME":
        return new GetHostNameCommand(beaconid, commandType, content);
      case "GETHOSTOSNAME":
        return new GetHostOsCommand(beaconid, commandType, content);
    }
    return null;
  }
}
