package com.reffians.c2.model.CommandTypes;

import com.reffians.c2.model.Command;
import org.springframework.stereotype.Component;

/**
 * Class to create the necessary Command classes (as part of a factory pattern).
 */
 @Component("CommandFactory")
public class CommandFactory {

    /**
     * Initializes a command class given the command type (i.e. STOP, SLEEP, UPLOAD)
     * @param commandType String indicating from which alert service this JSON payload came from.
     * @param 
     * @return the correct Command object.
     */
    public Command getCommand(Integer beaconid, String commandType, String content) {
        if (commandType == null){
            return null;
        } else if(commandType.equalsIgnoreCase("STOP")){
            return new StopCommand(beaconid, commandType, content);
        } else if (commandType.equalsIgnoreCase("SLEEP")){
            return new SleepCommand(beaconid, commandType, content);
        } else if (commandType.equalsIgnoreCase("UPLOAD")){
            return new UploadCommand(beaconid, commandType, content);
        } else if (commandType.equalsIgnoreCase("DOWNLOAD")){
            return new DownloadCommand(beaconid, commandType, content);
        } else if (commandType.equalsIgnoreCase("GETHOSTNAME")){
            return new GetHostNameCommand(beaconid, commandType, content);
        } else if (commandType.equalsIgnoreCase("GETHOSTOS")){
            return new GetHostOSCommand(beaconid, commandType, content);
        }
        return null;
    }
}
