package com.reffians.c2.service;

import com.reffians.c2.model.User;
import com.reffians.c2.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**User Service Class. **/
@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

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
   */
  public String retreiveHash(String username) {
    List<String> pword = userRepository.findPwordByUser(username);
    if (pword.size() == 1) {
      return pword.get(0);
    } 
    return null;
  }

  /** 
   * Checks if user exists in the database given a username.

   * @param username is a nonempty string corresponding to the username. 
   */
  public boolean userExists(String username) {
    List<User> users =  getUsers(username);
    if (!users.isEmpty()) {
      return true;
    }
    return false;
  } // return !getUsers(username).isEmpty();

  /** 
   * Retreives password hash if user exists in the database. 

   * @param username is a nonempty string corresponding to the username. 

   * @param password is a nonempty string corresponding to the plain text
   *     password.
   */
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

   * @param username is a nonempty string corresponding to the username.

   * @param password is a nonempty string corresponding to the plain text
   *     password.
   */
  public void insertUser(String username, String password) {
    //hash password
    String pwhash = BCrypt.hashpw(password, BCrypt.gensalt());
    userRepository.insertUser(username, pwhash);
  }
}
