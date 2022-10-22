package com.reffians.c2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.reffians.c2.model.Command.Status;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommandTest {
  @Test
  public void testGetBeaconidBeaconidZeroContentNotEmpty() {
    int beaconid = 0;
    Command command = new Command(beaconid, "foo");
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidZeroContentEmpty() {
    int beaconid = 0;
    Command command = new Command(beaconid, "");
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidZeroContentNull() {
    int beaconid = 0;
    Command command = new Command(beaconid, null);
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidNegativeContentNotEmpty() {
    int beaconid = -10;
    Command command = new Command(beaconid, "foo");
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidNegativeContentEmpty() {
    int beaconid = -10;
    Command command = new Command(beaconid, "");
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidNegativeContentNull() {
    int beaconid = -10;
    Command command = new Command(beaconid, null);
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidPositiveContentNotEmpty() {
    int beaconid = 5;
    Command command = new Command(beaconid, "foo");
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidPositiveContentEmpty() {
    int beaconid = 5;
    Command command = new Command(beaconid, "");
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidPositiveContentNull() {
    int beaconid = 5;
    Command command = new Command(beaconid, null);
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidNullContentNotEmpty() {
    Integer beaconid = null;
    Command command = new Command(beaconid, "foo");
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidNullContentEmpty() {
    Integer beaconid = null;
    Command command = new Command(beaconid, "");
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidNullContentNull() {
    Integer beaconid = null;
    Command command = new Command(beaconid, null);
    assertEquals(beaconid, command.getBeaconid());
  }

  @Test
  public void testGetBeaconidBeaconidMaxPositiveContentNonEmpty() {
    String content = "foo";
    Command command = new Command(Integer.MAX_VALUE, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetBeaconidBeaconidMaxPositiveContentEmpty() {
    String content = "";
    Command command = new Command(Integer.MAX_VALUE, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetBeaconidBeaconidMaxPositiveContentNull() {
    String content = null;
    Command command = new Command(Integer.MAX_VALUE, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetBeaconidBeaconidMinNegativeContentNonEmpty() {
    String content = "foo";
    Command command = new Command(Integer.MIN_VALUE, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetBeaconidBeaconidMinNegativeContentEmpty() {
    String content = "";
    Command command = new Command(Integer.MIN_VALUE, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetBeaconidBeaconidMinNegativeContentNull() {
    String content = null;
    Command command = new Command(Integer.MIN_VALUE, content);
    assertEquals(content, command.getContent());
  }


  @Test
  public void testGetContentBeaconidZeroContentNonEmpty() {
    String content = "foo";
    Command command = new Command(0, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidZeroContentEmpty() {
    String content = "";
    Command command = new Command(0, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidZeroContentNull() {
    String content = null;
    Command command = new Command(0, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidNegativeContentNonEmpty() {
    String content = "foo";
    Command command = new Command(-5, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidNegativeContentEmpty() {
    String content = "";
    Command command = new Command(-5, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidNegativeContentNull() {
    String content = null;
    Command command = new Command(-5, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidPositiveContentNonEmpty() {
    String content = "foo";
    Command command = new Command(5, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidPositiveContentEmpty() {
    String content = "";
    Command command = new Command(5, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidPositiveContentNull() {
    String content = null;
    Command command = new Command(5, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidNullContentNonEmpty() {
    String content = "foo";
    Command command = new Command(null, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidNullContentEmpty() {
    String content = "";
    Command command = new Command(null, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidNullContentNull() {
    String content = null;
    Command command = new Command(null, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidMaxPositiveContentNonEmpty() {
    String content = "foo";
    Command command = new Command(Integer.MAX_VALUE, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidMaxPositiveContentEmpty() {
    String content = "";
    Command command = new Command(Integer.MAX_VALUE, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidMaxPositiveContentNull() {
    String content = null;
    Command command = new Command(Integer.MAX_VALUE, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidMinNegativeContentNonEmpty() {
    String content = "foo";
    Command command = new Command(Integer.MIN_VALUE, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidMinNegativeContentEmpty() {
    String content = "";
    Command command = new Command(Integer.MIN_VALUE, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testGetContentBeaconidMinNegativeContentNull() {
    String content = null;
    Command command = new Command(Integer.MIN_VALUE, content);
    assertEquals(content, command.getContent());
  }

  @Test
  public void testNewCommandStatusBeaconidNullContentNotEmpty() {
    Command command = new Command(null, "foo");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidZeroContentNotEmpty() {
    Command command = new Command(0, "foo");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidPositiveContentNotEmpty() {
    Command command = new Command(5, "foo");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidNegativeContentNotEmpty() {
    Command command = new Command(-5, "foo");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidMaxPositiveContentNotEmpty() {
    Command command = new Command(Integer.MAX_VALUE, "foo");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidMinNegativeContentNotEmpty() {
    Command command = new Command(Integer.MIN_VALUE, "foo");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidNullContentEmpty() {
    Command command = new Command(null, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidZeroContentEmpty() {
    Command command = new Command(0, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidPositiveContentEmpty() {
    Command command = new Command(5, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidNegativeContentEmpty() {
    Command command = new Command(-5, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidMaxPositiveContentEmpty() {
    Command command = new Command(Integer.MAX_VALUE, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidMinNegativeContentEmpty() {
    Command command = new Command(Integer.MIN_VALUE, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidNullContentNull() {
    Command command = new Command(null, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidZeroContentNull() {
    Command command = new Command(0, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidPositiveContentNull() {
    Command command = new Command(5, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidNegativeContentNull() {
    Command command = new Command(-5, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidMaxPositiveContentNull() {
    Command command = new Command(Integer.MAX_VALUE, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testNewCommandStatusBeaconidMinNegativeContentNull() {
    Command command = new Command(Integer.MIN_VALUE, "");
    assertEquals(Status.pending, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidNullContentNotEmpty() {
    Command command = new Command(null, "foo");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidZeroContentNotEmpty() {
    Command command = new Command(0, "foo");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidPositiveContentNotEmpty() {
    Command command = new Command(5, "foo");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidNegativeContentNotEmpty() {
    Command command = new Command(-5, "foo");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidMaxPositiveContentNotEmpty() {
    Command command = new Command(Integer.MAX_VALUE, "foo");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidMinNegativeContentNotEmpty() {
    Command command = new Command(Integer.MIN_VALUE, "foo");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidNullContentEmpty() {
    Command command = new Command(null, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidZeroContentEmpty() {
    Command command = new Command(0, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidPositiveContentEmpty() {
    Command command = new Command(5, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidNegativeContentEmpty() {
    Command command = new Command(-5, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidMaxPositiveContentEmpty() {
    Command command = new Command(Integer.MAX_VALUE, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidMinNegativeContentEmpty() {
    Command command = new Command(Integer.MIN_VALUE, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidNullContentNull() {
    Command command = new Command(null, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidZeroContentNull() {
    Command command = new Command(0, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidPositiveContentNull() {
    Command command = new Command(5, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidNegativeContentNull() {
    Command command = new Command(-5, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidMaxPositiveContentNull() {
    Command command = new Command(Integer.MAX_VALUE, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }

  @Test
  public void testSetStatusBeaconidMinNegativeContentNull() {
    Command command = new Command(Integer.MIN_VALUE, "");
    command.setStatus(Status.sent);
    assertEquals(Status.sent, command.getStatus());
  }
}
