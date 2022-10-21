package com.reffians.c2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.lang.module.ResolutionException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.repository.CommandRepository;

@ExtendWith(MockitoExtension.class)
public class C2ServiceTest {
  @InjectMocks
  @Autowired
  private C2Service c2Service;

  @Mock
  private CommandRepository commandRepository;

  @Test
  public void getCommandsBeaconIdTest() {
    int beaconid = 0;
    Command command = new Command(beaconid, "foo");
    List<Command> commands = List.of(command);
    Mockito.when(commandRepository.findByBeaconid(beaconid)).thenReturn(commands);
    assertEquals(commands, c2Service.getCommands(beaconid));
  }

  @Test
  public void getCommandsBeaconIdStatusTest() {
    int beaconid = 0;
    Command command = new Command(beaconid, "foo");
    List<Command> commands = List.of(command);
    Mockito.when(commandRepository.findByBeaconidStatus(beaconid, Status.pending.name())).thenReturn(commands);
    assertEquals(commands, c2Service.getCommands(beaconid, Status.pending));
  }
}
