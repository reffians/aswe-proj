package com.reffians.c2.model.commands;

<<<<<<< HEAD:src/main/java/com/reffians/c2/model/commands/GetHostOsCommand.java
import javax.persistence.Entity;
import com.reffians.c2.exception.CommandContentMismatchException;
=======
import com.reffians.c2.model.Command;
import javax.persistence.Entity;
>>>>>>> 9c77782 (refactored all the status stuff + and added the {beacon/user}/result endpoints):src/main/java/com/reffians/c2/model/CommandTypes/GetHostOsCommand.java

/** A stop command data model, representing a command created by a user to be
  * executed by a specific beacon.
  */
@Entity
public class GetHostOsCommand extends Command{

  /** A constructor for the command data model.
    *
    * @param beaconid an integer representing the associated beacon.
    * @param content a user-defined string containing the command content to be
    *     executed by the beacon.
    */
  public GetHostOsCommand(Integer beaconid, String commandType, String content) throws
      CommandContentMismatchException {
    super(beaconid);
    setType(commandType);
    setCommandContent(content);
  }

  @Override
  public void checkTypeContent(String content) throws CommandContentMismatchException {
    if (!content.isEmpty()){
      throw new CommandContentMismatchException("GETHOSTOS", content);
    }
  }
}
