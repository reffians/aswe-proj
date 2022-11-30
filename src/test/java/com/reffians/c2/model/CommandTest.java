package com.reffians.c2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.reffians.c2.model.commands.Command.Status;

@SpringBootTest
public class CommandTest {
  // @Test
  // private void testGetBeaconidBeaconidZeroContentNotEmpty() {
  //   int beaconid = 0;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidZeroContentEmpty() {
  //   int beaconid = 0;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidZeroContentNull() {
  //   int beaconid = 0;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidNegContentNotEmpty() {
  //   int beaconid = -10;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidNegContentEmpty() {
  //   int beaconid = -10;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidNegContentNull() {
  //   int beaconid = -10;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidPosContentNotEmpty() {
  //   int beaconid = 5;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidPosContentEmpty() {
  //   int beaconid = 5;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidPosContentNull() {
  //   int beaconid = 5;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidNullContentNotEmpty() {
  //   Integer beaconid = null;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidNullContentEmpty() {
  //   Integer beaconid = null;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidNullContentNull() {
  //   Integer beaconid = null;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // private void testGetBeaconidBeaconidMaxPosContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetBeaconidBeaconidMaxPosContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetBeaconidBeaconidMaxPosContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetBeaconidBeaconidMinNegContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetBeaconidBeaconidMinNegContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetBeaconidBeaconidMinNegContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }


  // @Test
  // private void testGetContentBeaconidZeroContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(0, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidZeroContentEmpty() {
  //   String content = "";
  //   Command command = new Command(0, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidZeroContentNull() {
  //   String content = null;
  //   Command command = new Command(0, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidNegContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(-5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidNegContentEmpty() {
  //   String content = "";
  //   Command command = new Command(-5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidNegContentNull() {
  //   String content = null;
  //   Command command = new Command(-5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidPosContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidPosContentEmpty() {
  //   String content = "";
  //   Command command = new Command(5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidPosContentNull() {
  //   String content = null;
  //   Command command = new Command(5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidNullContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(null, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidNullContentEmpty() {
  //   String content = "";
  //   Command command = new Command(null, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidNullContentNull() {
  //   String content = null;
  //   Command command = new Command(null, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidMaxPosContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidMaxPosContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidMaxPosContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidMinNegContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidMinNegContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testGetContentBeaconidMinNegContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidNullContentNotEmpty() {
  //   Command command = new Command(null, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidZeroContentNotEmpty() {
  //   Command command = new Command(0, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidPosContentNotEmpty() {
  //   Command command = new Command(5, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidNegContentNotEmpty() {
  //   Command command = new Command(-5, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidMaxPosContentNotEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidMinNegContentNotEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidNullContentEmpty() {
  //   Command command = new Command(null, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidZeroContentEmpty() {
  //   Command command = new Command(0, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidPosContentEmpty() {
  //   Command command = new Command(5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidNegContentEmpty() {
  //   Command command = new Command(-5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidMaxPosContentEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidMinNegContentEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidNullContentNull() {
  //   Command command = new Command(null, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidZeroContentNull() {
  //   Command command = new Command(0, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidPosContentNull() {
  //   Command command = new Command(5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidNegContentNull() {
  //   Command command = new Command(-5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidMaxPosContentNull() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testNewCommandStatusBeaconidMinNegContentNull() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidNullContentNotEmpty() {
  //   Command command = new Command(null, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidZeroContentNotEmpty() {
  //   Command command = new Command(0, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidPosContentNotEmpty() {
  //   Command command = new Command(5, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidNegContentNotEmpty() {
  //   Command command = new Command(-5, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidMaxPosContentNotEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidMinNegContentNotEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidNullContentEmpty() {
  //   Command command = new Command(null, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidZeroContentEmpty() {
  //   Command command = new Command(0, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidPosContentEmpty() {
  //   Command command = new Command(5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidNegContentEmpty() {
  //   Command command = new Command(-5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidMaxPosContentEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidMinNegContentEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidNullContentNull() {
  //   Command command = new Command(null, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidZeroContentNull() {
  //   Command command = new Command(0, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidPosContentNull() {
  //   Command command = new Command(5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidNegContentNull() {
  //   Command command = new Command(-5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidMaxPosContentNull() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // private void testSetStatusBeaconidMinNegContentNull() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }
}
