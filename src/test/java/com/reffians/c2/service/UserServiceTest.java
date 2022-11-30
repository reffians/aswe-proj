package com.reffians.c2.service;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import com.reffians.c2.exception.UserExistsException;
import com.reffians.c2.exception.UserMissingException;
import com.reffians.c2.model.User;
import com.reffians.c2.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class UserServiceTest {
  @Autowired
  private UserService userService;

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private BCryptPasswordEncoder passwordEncoder;

  @Test
  public void getUserExistsTest()  {
    String username = "user";
    User user = mock(User.class);
    Mockito.when(userRepository.findByUsername(username)).thenReturn(List.of(user));
    assertDoesNotThrow(() -> {
      assertEquals(user, userService.getUser(username));
    }); 
  }

  @Test
  public void getUserNExistsTest()  {
    String username = "user";
    Mockito.when(userRepository.findByUsername(username)).thenReturn(emptyList());
    assertThrows(UserMissingException.class, () -> {
      userService.getUser(username);
    }); 
  }

  @Test
  public void userExistsExistsTest()  {
    String username = "user";
    List<User> users = List.of(mock(User.class));
    Mockito.when(userRepository.findByUsername(username)).thenReturn(users);
    assertTrue(userService.userExists(username));
  }

  @Test
  public void userExistsNExistsTest()  {
    String username = "user";
    Mockito.when(userRepository.findByUsername(username)).thenReturn(emptyList());
    assertFalse(userService.userExists(username));
  }

  @Test
  public void passwordMatchesMatchesTest() {
    String rawPassword = "raw";
    String encodedPassword = "encoded";
    Mockito.when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
    assertTrue(userService.passwordMatches(rawPassword, encodedPassword));
  }

  @Test
  public void passwordMatchesNMatchesTest() {
    String rawPassword = "raw";
    String encodedPassword = "encoded";
    Mockito.when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);
    assertFalse(userService.passwordMatches(rawPassword, encodedPassword));
  }

  @Test
  public void addUserExistsTest() {
    String username = "user";
    Mockito.when(userRepository.findByUsername(username)).thenReturn(List.of(mock(User.class)));
    assertThrows(UserExistsException.class, () -> {
      userService.addUser(username, "password");
    });
  }

  @Test
  public void addUserNExistsEncodedPasswordTest() {
    String username = "user";
    User user = mock(User.class);
    Mockito.when(userRepository.findByUsername(username)).thenReturn(emptyList());
    Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
    assertDoesNotThrow(() -> {
      assertEquals(user, userService.addUser(username, "password"));
    });
  }
}
