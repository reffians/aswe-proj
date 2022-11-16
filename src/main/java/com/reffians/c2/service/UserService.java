package com.reffians.c2.service;

import com.reffians.c2.exception.UserExistsException;
import com.reffians.c2.exception.UserMissingException;
import com.reffians.c2.model.User;
import com.reffians.c2.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/** User Service Class. **/
@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  /** Get user with this username from the database.
    *
    * @param username a non-empty string identifying a user.
    * @return a user object corresponding to the username.
    * @throws UserMissingException if username does not belong to any user.
    */
  public User getUser(String username) throws UserMissingException {
    List<User> users = userRepository.findByUsername(username);
    if (users.isEmpty()) {
      throw new UserMissingException(username);
    }
    return users.get(0);
  }

  /** 
    * Checks if user exists in the database given a username.
    *
    * @param username is a nonempty string identifying a user.
    * @return a boolean indicating whether a user with this username exists.
    */
  public boolean userExists(String username) {
    return !userRepository.findByUsername(username).isEmpty();
  }

  /** 
    * Check whether the encoded password matches the raw password after it is encoded.
    *
    * @param rawPassword is a nonempty string corresponding to the plain text password. 
    * @param encodedPassword is a nonempty string corresponding to the encoded password.
    * @return a boolean indicating whether the password matches.
    */
  public boolean passwordMatches(String rawPassword, String encodedPassword) { 
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }

  /** 
    * Adds a new user to the database.
    *
    * @param username is a nonempty string corresponding to the username.
    * @param rawPassword is a nonempty string corresponding to the plain text
    *     password.
    * @return the saved user object.
    * @throws UserExistsException if user already exists in the database.
    */
  public User addUser(String username, String rawPassword) throws UserExistsException {
    if (userExists(username)) {
      throw new UserExistsException(username);
    }
    return userRepository.save(new User(username, rawPassword, passwordEncoder));
  }
}
