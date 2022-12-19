package com.reffians.c2.model.commands;

import com.reffians.c2.exception.CommandContentMismatchException;
import com.reffians.c2.exception.InvalidCommandTypeException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.NoArgsConstructor;


/**
 * Class to create the necessary Command classes (as part of a factory pattern).
 */
@NoArgsConstructor
public class CommandFactory {
  /** Initializes a command class given the command type (i.e. STOP, SLEEP, EXECUTE)
   *
   * @param commandType String indicating from which alert service this JSON payload came from.
   * @param beaconid Int id for beac
   * @return the correct Command object.
   */
  public static Command getCommand(@NotNull Integer beaconid, @NotEmpty String commandType,
      String content) throws CommandContentMismatchException, InvalidCommandTypeException {
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
      case "GETHOSTOS":
        return new GetHostOsCommand(beaconid, content);
      default:
        throw new InvalidCommandTypeException(commandType);
    }
  }
}
