package com.reffians.c2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.repository.CommandRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class C2ServiceTest {
  @InjectMocks
  @Autowired
  private C2Service c2Service;

  @Mock
  private CommandRepository commandRepository;

  @Test
  public void testGetCommandsBeaconIdSingleCommandSingleBeaconid() {
    int beaconid = 0;
    Command command = new Command(beaconid, "foo");
    List<Command> commands = List.of(command);
    Mockito.when(commandRepository.findByBeaconid(beaconid)).thenReturn(commands);
    assertEquals(commands, c2Service.getCommands(beaconid));
  }

  @Test
  public void testGetCommandsBeaconIdStatus() {
    int beaconid = 0;
    Command command = new Command(beaconid, "foo");
    List<Command> commands = List.of(command);
    Mockito.when(commandRepository.findByBeaconidStatus(beaconid, Status.pending.name()))
      .thenReturn(commands);
    assertEquals(commands, c2Service.getCommands(beaconid, Status.pending));
  }

  @Test
  public void testUpdateCommandStatusOldStatusSingle() {
    Command command = new Command(0, "foo");
    List<Command> commands = List.of(command);
    Mockito.when(commandRepository.save(command)).thenReturn(command);
    List<Command> updatedCommands = c2Service.updateCommandStatus(commands, Status.pending,
        Status.sent);
    assertEquals(1, updatedCommands.size());
    for (Command updatedCommand : updatedCommands) {
      assertEquals(Status.sent, updatedCommand.getStatus());
    }
  }

  @Test
  public void testUpdateCommandStatusNotOldStatusSingle() {
    Command command = new Command(0, "foo");
    List<Command> commands = List.of(command);
    assertTrue(c2Service.updateCommandStatus(commands, Status.sent, Status.finished).isEmpty());
  }

  @Test
  public void testUpdateCommandStatusMultipleSameOldStatus() {
    Command command0 = new Command(0, "foo");
    Command command1 = new Command(1, "foo");
    Command command2 = new Command(2, "foo");
    Mockito.when(commandRepository.save(command0)).thenReturn(command0);
    Mockito.when(commandRepository.save(command1)).thenReturn(command1);
    Mockito.when(commandRepository.save(command2)).thenReturn(command2);
    List<Command> commands = List.of(command0, command1, command2);
    List<Command> updatedCommands = c2Service.updateCommandStatus(commands, Status.pending,
        Status.finished);
    assertEquals(3, updatedCommands.size());
    for (Command updatedCommand : updatedCommands) {
      assertEquals(Status.finished, updatedCommand.getStatus());
    }
  }

  @Test
  public void testUpdateCommandStatusMultipleSameNotOldStatus() {
    Command command0 = new Command(0, "foo");
    Command command1 = new Command(1, "foo");
    Command command2 = new Command(2, "foo");
    List<Command> commands = List.of(command0, command1, command2);
    List<Command> updatedCommands = c2Service.updateCommandStatus(commands, Status.sent,
        Status.finished);
    assertTrue(updatedCommands.isEmpty());
  }

  @Test
  public void testUpdateCommandStatusMultipleTwoOldStatus() {
    Command command0 = new Command(0, "foo");
    Command command1 = new Command(1, "foo");
    command1.setStatus(Status.executed);
    Command command2 = new Command(2, "foo");
    Mockito.when(commandRepository.save(command0)).thenReturn(command0);
    Mockito.when(commandRepository.save(command2)).thenReturn(command2);
    List<Command> commands = List.of(command0, command1, command2);
    List<Command> updatedCommands = c2Service.updateCommandStatus(commands, Status.pending,
        Status.finished);
    assertEquals(2, updatedCommands.size());
    for (Command updatedCommand : updatedCommands) {
      assertEquals(Status.finished, updatedCommand.getStatus());
    }
  }
}
