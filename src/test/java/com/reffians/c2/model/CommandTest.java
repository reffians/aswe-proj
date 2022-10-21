package com.reffians.c2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.reffians.c2.model.Command.Status;

@SpringBootTest
public class CommandTest {
  @Test
  public void testGetBeaconid() {
    int beaconid = 0;
    Command command = new Command(beaconid, "foo");
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetContent() {
    String content = "foo";
    Command command = new Command(0, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testNewCommandStatus() {
    Command command = new Command(0, "foo");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testSetStatus() {
    Command command = new Command(0, "foo");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }
}
