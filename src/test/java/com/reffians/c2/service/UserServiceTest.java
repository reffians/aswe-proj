package com.reffians.c2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.reffians.c2.model.User;
import com.reffians.c2.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTest {
  @Autowired
  private UserService userService;

  @MockBean
  private UserRepository userRepository;

  @Test
  public void getUsersTest()  {
    String username = "Nikhil";
    String password = "pword";
    User user = new User(username, password);
    List<User> users = List.of(user);
    Mockito.when(userRepository.findByUsername(username)).thenReturn(users);
    assertEquals(users, userService.getUsers(username)); 
  }

  @Test
  public void compareHashTest(){
    String username = "Nikhil";
    String password = "pword";
    UserService service = Mockito.mock(UserService.class);
    service.addUser(username, password);
    Mockito.doReturn(true).when(service).compareHash(username, password);
    assertEquals(true, service.compareHash(username, password));
  }


  @Test
  public void compareHashTestEmpty(){
    String username = "Nikhil";
    String password = "";
    UserService service = Mockito.mock(UserService.class);
    service.addUser(username, password);
    Mockito.doReturn(true).when(service).compareHash(username, password);
    assertEquals(true, service.compareHash(username, password));
  }

  @Test
  public void userExistsTest(){
    String username = "Nikhil";
    String password = "pword";
    UserService service = Mockito.mock(UserService.class);
    service.addUser(username, password);
    Mockito.doReturn(true).when(service).userExists(username);
    assertEquals(true, service.userExists(username));
  }

  @Test
  public void wrongUserExistsTest(){
    String username = "Nikhil";
    String password = "pword";
    String wrong_username = "nik";
    boolean res = false;
    UserService service = Mockito.mock(UserService.class);
    service.addUser(username, password);
    Mockito.doReturn(res).when(service).userExists(wrong_username);
    assertEquals(res, service.userExists(wrong_username));
  }
}
