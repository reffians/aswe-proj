package com.reffians.c2.model.commands;

import com.reffians.c2.exception.CommandContentMismatchException;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * Class to create the necessary Command classes (as part of a factory pattern).
 */
@NoArgsConstructor
public class CommandFactory {
  /** Initializes a command class given the command type (i.e. STOP, SLEEP, EXECUTE)
    *
    * @param commandType String indicating from which alert service this JSON payload came from.
    * @param 
    * @return the correct Command object.
    */
  public static Command getCommand(@NotNull Integer beaconid, @NotEmpty String commandType,
      String content) throws CommandContentMismatchException, IllegalArgumentException {
    switch (commandType) {
      case "STOP":
        return new StopCommand(beaconid, content);
      case "SLEEP":
        return new SleepCommand(beaconid, content);
      case "EXECUTE":
        return new ExecuteCommand(beaconid, content);
      case "DOWNLOAD":
        return new DownloadCommand(beaconid, content);
      case "GETHOSTNAME":
        return new GetHostNameCommand(beaconid, content);
      case "GETHOSTOSNAME":
        return new GetHostOsCommand(beaconid, content);
      default:
        throw new IllegalArgumentException(String.format("Unknown commandType %s", commandType));
    }
  }
}
