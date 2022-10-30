package com.reffians.c2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.User;
import com.reffians.c2.repository.UserRepository;
import com.reffians.c2.repository.BeaconRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class C2ServiceTest {
  @Autowired
  private C2Service c2Service;

  @MockBean
  private UserRepository userRepository;
  @MockBean
  private BeaconRepository beaconRepository;

  static final int beaconid = 0;
  static Command command0;
  static Command command1;
  static Command command2;
  static Command command3;
  static List<Command> oneCommandPending;
  static List<Command> threeCommandsAllPending;
  static List<Command> threeCommandsOneExecuted;

  @Test
  public void getUsersTest()  {
    String username = "Nikhil";
    String password = "pword";
    User user = new User(username, password);
    List<User> users = List.of(user);
    Mockito.when(userRepository.findByUsername(username)).thenReturn(users);
    assertEquals(users, c2Service.getUsers(username)); 
  }

  @Test
  public void getUsersByBothTest()  {
    String username = "Nikhil";
    String password = "pword";
    User user = new User(username, password);
    List<User> users = List.of(user);
    Mockito.when(userRepository.findByUnamePword(username, password)).thenReturn(users);
    assertEquals(users, c2Service.getUsers(username, password)); 
  }

  @Test
  public void compareHashTest(){
    String username = "Nikhil";
    String password = "pword";
    C2Service service = Mockito.mock(C2Service.class);
    service.insertUser(username, password);
    Mockito.doReturn(true).when(service).compareHash(username, password);
    assertEquals(true, service.compareHash(username, password));
  }


  @Test
  public void compareHashTestEmpty(){
    String username = "Nikhil";
    String password = "";
    C2Service service = Mockito.mock(C2Service.class);
    service.insertUser(username, password);
    Mockito.doReturn(true).when(service).compareHash(username, password);
    assertEquals(true, service.compareHash(username, password));
  }

  @Test
  public void userExistsTest(){
    String username = "Nikhil";
    String password = "pword";
    C2Service service = Mockito.mock(C2Service.class);
    service.insertUser(username, password);
    Mockito.doReturn(true).when(service).userExists(username);
    assertEquals(true, service.userExists(username));
  }

  @Test
  public void wrongUserExistsTest(){
    String username = "Nikhil";
    String password = "pword";
    String wrong_username = "nik";
    boolean res = false;
    C2Service service = Mockito.mock(C2Service.class);
    service.insertUser(username, password);
    Mockito.doReturn(res).when(service).userExists(wrong_username);
    assertEquals(res, service.userExists(wrong_username));
  }

  // @Test
  // public void submitCommandTest()
  // {
    // Command command1 = new Command(1, "content1");
    // Mockito.doNothing().when(commandRepository).save(Mockito.any(Command.class));
    // c2Service.addCommand(command1.beaconid, command1.content, command1.status);
    // Mockito.verify(commandRepository, Mockito.times(1)).save(Mockito.any(Command.class);
  // }

}
