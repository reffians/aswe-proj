package com.reffians.c2.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.reffians.c2.model.commands.Command;
import com.reffians.c2.repository.CommandRepository;

@SpringBootTest
class CommandServiceTest {
  @Autowired
  private CommandService commandService;

  @MockBean
  private CommandRepository commandRepository;
  //TODO: test getNotSentCommands

  //test addCommand
  @Test
  void testAddCommand() {
    int beaconid = 0;
    String type = "STOP";
    String content = "";
    Command command = new Command(beaconid);
    assertDoesNotThrow(()->{
    command.setCommandContent(content);
    command.setType(type);
    Mockito.when(commandRepository.save(any(Command.class))).thenReturn(command);
      assertEquals(command, commandService.addCommand(beaconid, type, content));
    });
  }

// test getCommand
  @Test
  void testGetBeaconForCommand() {
    int commandid = 0;
    int beaconid = 1;
    Mockito.when(commandRepository.findBeaconForCommand(commandid)).thenReturn(beaconid);
    assertDoesNotThrow(()->{
      assertEquals(beaconid, commandService.getBeaconForCommand(commandid));
    });
  }
}
