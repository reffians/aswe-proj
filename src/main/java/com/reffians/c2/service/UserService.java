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

  /** 
   * Retreives password hash for a given username in the database. 

   * @param username is a nonempty string corresponding to the username
   */
  public String getPasswordHash(String username) {
    List<String> encodedPasswords = userRepository.findEncodedPasswordByUsername(username);
    return encodedPasswords.isEmpty() ? null : encodedPasswords.get(0);
  }

  /** 
   * Checks if user exists in the database given a username.

   * @param username is a nonempty string corresponding to the username. 
   */
  public boolean userExists(String username) {
    return !(getUsers(username).isEmpty());
  }

  /** 
   * Retreives password hash if user exists in the database. 

   * @param username is a nonempty string corresponding to the username. 

   * @param password is a nonempty string corresponding to the plain text
   *     password.
   */
  public boolean compareHash(String username, String password) { 
    String pwhash = getPasswordHash(username);
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
  public void addUser(String username, String password) {
    //hash password
    String pwhash = BCrypt.hashpw(password, BCrypt.gensalt());
    userRepository.save(new User(username, pwhash));
  }
}
