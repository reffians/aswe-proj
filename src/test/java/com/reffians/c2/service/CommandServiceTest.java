package com.reffians.c2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.reffians.c2.exception.CommandContentMismatchException;
import com.reffians.c2.model.commands.Command;
import com.reffians.c2.model.commands.CommandFactory;
import com.reffians.c2.model.commands.Command.Status;
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
class CommandServiceTest {
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
  static void beforeClass() throws CommandContentMismatchException {
    command0 = CommandFactory.getCommand(0, "STOP", "");
    command1 = CommandFactory.getCommand(1, "SLEEP", "15");
    command2 = CommandFactory.getCommand(2, "SLEEP", "300");
    command3 = CommandFactory.getCommand(3, "GETHOSTNAME", "");
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
  void getCommandsBeaconIdTest() throws CommandContentMismatchException {
    Command command = CommandFactory.getCommand(beaconid, "STOP", "");
    List<Command> commands = List.of(command);
    Mockito.when(commandRepository.findByBeaconid(beaconid)).thenReturn(commands);
    assertEquals(commands, commandService.getCommands(beaconid));
  }

  @Test
  void testGetCommandsBeaconIdSingleCommandSingleBeaconid() {
    Mockito.when(commandRepository.findByBeaconid(beaconid)).thenReturn(oneCommandPending);
    assertEquals(oneCommandPending, commandService.getCommands(beaconid));
  }

  @Test
  void testGetCommandsBeaconIdStatus() {
    Mockito.when(commandRepository.findByBeaconidStatus(beaconid, Status.pending.name()))
        .thenReturn(oneCommandPending);
    assertEquals(oneCommandPending, commandService.getCommands(beaconid, Status.pending));
  }

  @Test
  void testUpdateCommandStatusSingleUpdate() {
    Mockito.when(commandRepository.save(command0)).thenReturn(command0);
    List<Command> updatedCommands = commandService.updateCommandStatus(oneCommandPending,
        Status.sent);
    assertEquals(1, updatedCommands.size());
    for (Command updatedCommand : updatedCommands) {
      assertEquals(Status.sent, updatedCommand.getStatus());
    }
  }

  @Test
  void testUpdateCommandStatusSingleNoUpdate() {
    assertTrue(commandService.updateCommandStatus(oneCommandPending, Status.pending)
        .isEmpty());
  }

  @Test
  void testUpdateCommandStatusMultipleAllUpdate() {
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
  void testUpdateCommandStatusMultipleSameNoneUpdate() {
    assertTrue(commandService.updateCommandStatus(threeCommandsAllPending, Status.pending)
        .isEmpty());
  }

  @Test
  void testUpdateCommandStatusMultipleTwoUpdated() {
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
