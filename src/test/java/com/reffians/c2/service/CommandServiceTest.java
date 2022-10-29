package com.reffians.c2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.repository.CommandRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CommandServiceTest {
  @Autowired
  private CommandService commandService;
  @MockBean
  private CommandRepository commandRepository;

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
    Command command = new Command(beaconid, "foo");
    List<Command> commands = List.of(command);
    Mockito.when(commandRepository.findByBeaconid(beaconid)).thenReturn(commands);
    assertEquals(commands, commandService.getCommands(beaconid));
  }

  @Test
  public void testGetCommandsBeaconIdSingleCommandSingleBeaconid() {
    Mockito.when(commandRepository.findByBeaconid(beaconid)).thenReturn(oneCommandPending);
    assertEquals(oneCommandPending, commandService.getCommands(beaconid));
  }

  @Test
  public void testGetCommandsBeaconIdStatus() {
    Mockito.when(commandRepository.findByBeaconidStatus(beaconid, Status.pending.name()))
        .thenReturn(oneCommandPending);
    assertEquals(oneCommandPending, commandService.getCommands(beaconid, Status.pending));
  }

  @Test
  public void testUpdateCommandStatusSingleUpdate() {
    Mockito.when(commandRepository.save(command0)).thenReturn(command0);
    List<Command> updatedCommands = commandService.updateCommandStatus(oneCommandPending,
        Status.sent);
    assertEquals(1, updatedCommands.size());
    for (Command updatedCommand : updatedCommands) {
      assertEquals(Status.sent, updatedCommand.getStatus());
    }
  }

  @Test
  public void testUpdateCommandStatusSingleNoUpdate() {
    assertTrue(commandService.updateCommandStatus(oneCommandPending, Status.pending)
        .isEmpty());
  }

  @Test
  public void testUpdateCommandStatusMultipleAllUpdate() {
    Mockito.when(commandRepository.save(command0)).thenReturn(command0);
    Mockito.when(commandRepository.save(command1)).thenReturn(command1);
    Mockito.when(commandRepository.save(command2)).thenReturn(command2);
    List<Command> updatedCommands = commandService.updateCommandStatus(
        threeCommandsAllPending, Status.finished);
    assertEquals(3, updatedCommands.size());
    for (Command updatedCommand : updatedCommands) {
      assertEquals(Status.finished, updatedCommand.getStatus());
    }
  }

  @Test
  public void testUpdateCommandStatusMultipleSameNoneUpdate() {
    assertTrue(commandService.updateCommandStatus(threeCommandsAllPending, Status.pending)
        .isEmpty());
  }

  @Test
  public void testUpdateCommandStatusMultipleTwoUpdated() {
    Mockito.when(commandRepository.save(command0)).thenReturn(command0);
    Mockito.when(commandRepository.save(command1)).thenReturn(command1);
    List<Command> updatedCommands = commandService.updateCommandStatus(
      threeCommandsOneExecuted, Status.sent);
    assertEquals(2, updatedCommands.size());
    for (Command updatedCommand : updatedCommands) {
      assertEquals(Status.sent, updatedCommand.getStatus());
    }
  }
}
