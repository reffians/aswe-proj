package com.reffians.c2.service;

import com.reffians.c2.model.Beacon;
import com.reffians.c2.model.User;
import com.reffians.c2.repository.BeaconRepository;
import com.reffians.c2.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**C2 Service Class. **/
@Service
public class C2Service {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private BeaconRepository beaconRepository;

  // registration and login methods
  public List<User> getUsers(String username) {
    return userRepository.findByUsername(username);
  }

  public List<User> getUsers(String username, String password) {
    return userRepository.findByUnamePword(username, password);
  }

  /** Retreives password hash for a given username in the database. **/
  public String getPwordHash(String username) {
    List<String> pword = userRepository.findPwordByUser(username);
    if (pword.size() == 1) {
      return pword.get(0);
    } 
    return null;
  }

  /** checkUser. Checks if user exists in the database given a username **/
  public boolean checkUser(String username) {
    List<User> users =  getUsers(username);
    if (users.size() == 0) {
      return true;
    }
    return false;
  }

  /** login. Attempts to login user. Retreives password hash if user exists in the database. **/
  public boolean login(String username, String password) {
    String pwhash = getPwordHash(username);
    if (pwhash == null) {
      return false;
    }
    //hash password
    return BCrypt.checkpw(password, pwhash);
  }

  /** Hashes password and adds user credentials to the database. **/
  public void addUser(String username, String password) {
    //hash password
    String pwhash = BCrypt.hashpw(password, BCrypt.gensalt());
    userRepository.insertUser(username, pwhash);
  }

  /**
   * Method to create beacon.

   * @param username username of the user that 'owns' this beacon
   */
  public void createBeacon(String username) {
    Beacon b = new Beacon(username);
    beaconRepository.save(b);
  }
  
}