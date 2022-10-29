package com.reffians.c2.service;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.model.User;
import com.reffians.c2.repository.BeaconRepository;
import com.reffians.c2.repository.CommandRepository;
import com.reffians.c2.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**C2 Service Class. **/
@Service
public class C2Service {
  @Autowired
  private CommandRepository commandRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private BeaconRepository beaconRepository;

  /** Get a list of commands by beaconid, updating status from pending to sent.
    *
    * @param beaconid an integer used to identify a beacon.
    * @return list of command objects associated with the specified beaconid.
    */
  public List<Command> getCommands(Integer beaconid) {
    List<Command> commands = commandRepository.findByBeaconid(beaconid);
    this.updateCommandStatus(commands, Status.pending, Status.sent);
    return commands;
  }

  /** Get a list of commands by beaconid and status, updating status from pending
    * to sent.
    *
    * @param beaconid an integer used to identify a beacon.
    * @return list of command objects associated with the specified beaconid and
    *     status.
    */
  public List<Command> getCommands(Integer beaconid, Status status) {
    List<Command> commands = commandRepository.findByBeaconidStatus(beaconid, status.name());
    this.updateCommandStatus(commands, Status.pending, Status.sent);
    return commands;
  }

  // registration and login methods
  public List<User> getUsers(String username) {
    return userRepository.findByUsername(username);
  }

  public List<User> getUsers(String username, String password) {
    return userRepository.findByUnamePword(username, password);
  } //mark for deletion

  /** 
   * Retreives password hash for a given username in the database. 
   * @param username is a nonempty string corresponding to the username
   * **/
  public String retreiveHash(String username) {
    List<String> pword = userRepository.findPwordByUser(username);
    if (pword.size() == 1) {
      return pword.get(0);
    } 
    return null;
  }

  /** 
   * Checks if user exists in the database given a username.
   * 
   * @param username is a nonempty string corresponding to the username. 
   * **/
  public boolean userExists(String username) {
    List<User> users =  getUsers(username);
    if (!users.isEmpty()) {
      return true;
    }
    return false;
  } // return !getUsers(username).isEmpty();

  /** 
   * Retreives password hash if user exists in the database. 
   * 
   * @param username is a nonempty string corresponding to the username. 

   * @param password is a nonempty string corresponding to the plain text
   * password.
   * **/
  public boolean compareHash(String username, String password) { 
    String pwhash = retreiveHash(username);
    if (pwhash == null) {
      return false;
    }
    //hash password
    return BCrypt.checkpw(password, pwhash);
  }

  /** 
   * Adds user credentials to the database consisting of the
   * username and hashed password. This method is run after checking
   * if the user exists 
   * 
   * @param username is a nonempty string corresponding to the username.

   * @param password is a nonempty string corresponding to the plain text
   * password.
   **/
  public void insertUser(String username, String password) {
    //hash password
    String pwhash = BCrypt.hashpw(password, BCrypt.gensalt());
    userRepository.insertUser(username, pwhash);
  }

  /** Update the status of commands which have oldStatus to newStatus, returning
    * a list of updated commands.
    *
    * @param commands a list of commands to be updated to newStatus should they be
    *     of oldStatus.
    * @param oldStatus the status that commands should have to be updated.
    * @param newStatus the status that the commands will be updated to.
    * @return a list of command objects that have been updated.
    */
  public List<Command> updateCommandStatus(List<Command> commands, Status oldStatus,
      Status newStatus) {
    ArrayList<Command> updatedCommands = new ArrayList<Command>();
    for (Command command : commands) {
      if (command.getStatus() == oldStatus) {
        command.setStatus(newStatus);
        updatedCommands.add(commandRepository.save(command));
      }
    }
    return updatedCommands;
  }

  /**
   * Method to create beacon.

   * @param username username of the user that 'owns' this beacon
   */
  public void createBeacon(String username) {
    beaconRepository.createBeacon(username);
  }

  /** Post to commands table. */
  public void addCommand(Integer beaconid, String content) {
    commandRepository.save(new Command(beaconid, content));
  }
  
}