package com.reffians.c2.model.CommandTypes;

import com.reffians.c2.model.Command;

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
  public GetHostOsCommand(Integer beaconid, String commandType, String content) {
    super(beaconid);
    setType(commandType);
    setCommandContent(content);
  }

  @Override
  public void checkTypeContent(String content) throws IllegalArgumentException{
    if (!content.isEmpty()){
      throw new IllegalArgumentException("The content you are trying to add does not match the command type. Please change the command type or the content.");
    }
  }
}
