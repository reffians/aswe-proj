package com.reffians.c2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.model.User;
import com.reffians.c2.repository.CommandRepository;
import com.reffians.c2.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
  private CommandRepository commandRepository;
  @MockBean
  private UserRepository userRepository;


  static final int beaconid = 0;
  static Command command0;
  static Command command1;
  static Command command2;
  static Command command3;
  static List<Command> oneCommandPending;
  static List<Command> threeCommandsAllPending;
  static List<Command> threeCommandsOneExecuted;

  @BeforeAll
  static void beforeClass() {
    command0 = new Command(beaconid, "foo");
    command1 = new Command(1, "bar");
    command2 = new Command(2, "foobar");
    command3 = new Command(3, "foofoobar");
    command3.setStatus(Status.executed);
    oneCommandPending = List.of(command0);
    threeCommandsAllPending = List.of(command0, command1, command2);
    threeCommandsOneExecuted = List.of(command0, command3, command1);
  }

  @AfterEach
  void afterEach() {
    command0.setStatus(Status.pending);
    command1.setStatus(Status.pending);
    command2.setStatus(Status.pending);
    command3.setStatus(Status.executed);
  }

  @Test
  public void getCommandsBeaconIdTest() {
    int beaconid = 0;
    Command command = new Command(beaconid, "foo");
    List<Command> commands = List.of(command);
    Mockito.when(commandRepository.findByBeaconid(beaconid)).thenReturn(commands);
    assertEquals(commands, c2Service.getCommands(beaconid));
  }

  @Test
  public void testGetCommandsBeaconIdSingleCommandSingleBeaconid() {
    Mockito.when(commandRepository.findByBeaconid(beaconid)).thenReturn(oneCommandPending);
    assertEquals(oneCommandPending, c2Service.getCommands(beaconid));
  }

  @Test
  public void testGetCommandsBeaconIdStatus() {
    Mockito.when(commandRepository.findByBeaconidStatus(beaconid, Status.pending.name()))
        .thenReturn(oneCommandPending);
    assertEquals(oneCommandPending, c2Service.getCommands(beaconid, Status.pending));
  }

  @Test
  public void testUpdateCommandStatusOldStatusSingle() {
    Mockito.when(commandRepository.save(command0)).thenReturn(command0);
    List<Command> updatedCommands = c2Service.updateCommandStatus(oneCommandPending,
        Status.pending, Status.sent);
    assertEquals(1, updatedCommands.size());
    for (Command updatedCommand : updatedCommands) {
      assertEquals(Status.sent, updatedCommand.getStatus());
    }
  }

  @Test
  public void testUpdateCommandStatusNotOldStatusSingle() {
    assertTrue(c2Service.updateCommandStatus(oneCommandPending, Status.sent,
        Status.finished).isEmpty());
  }

  @Test
  public void testUpdateCommandStatusMultipleSameOldStatus() {
    Mockito.when(commandRepository.save(command0)).thenReturn(command0);
    Mockito.when(commandRepository.save(command1)).thenReturn(command1);
    Mockito.when(commandRepository.save(command2)).thenReturn(command2);
    List<Command> updatedCommands = c2Service.updateCommandStatus(threeCommandsAllPending,
        Status.pending, Status.finished);
    assertEquals(3, updatedCommands.size());
    for (Command updatedCommand : updatedCommands) {
      assertEquals(Status.finished, updatedCommand.getStatus());
    }
  }

  @Test
  public void testUpdateCommandStatusMultipleSameNotOldStatus() {
    assertTrue(c2Service.updateCommandStatus(threeCommandsAllPending, Status.sent,
        Status.finished).isEmpty());
  }

  @Test
  public void testUpdateCommandStatusMultipleTwoOldStatus() {
    Mockito.when(commandRepository.save(command0)).thenReturn(command0);
    Mockito.when(commandRepository.save(command1)).thenReturn(command1);
    List<Command> updatedCommands = c2Service.updateCommandStatus(threeCommandsOneExecuted,
        Status.pending, Status.finished);
    assertEquals(2, updatedCommands.size());
    for (Command updatedCommand : updatedCommands) {
      assertEquals(Status.finished, updatedCommand.getStatus());
    }
  }

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
}
