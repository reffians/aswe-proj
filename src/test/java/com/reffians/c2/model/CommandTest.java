package com.reffians.c2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.reffians.c2.model.commands.Command.Status;

@SpringBootTest
class CommandTest {
  // @Test
  // void testGetBeaconidBeaconidZeroContentNotEmpty() {
  //   int beaconid = 0;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidZeroContentEmpty() {
  //   int beaconid = 0;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidZeroContentNull() {
  //   int beaconid = 0;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNegContentNotEmpty() {
  //   int beaconid = -10;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNegContentEmpty() {
  //   int beaconid = -10;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNegContentNull() {
  //   int beaconid = -10;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidPosContentNotEmpty() {
  //   int beaconid = 5;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidPosContentEmpty() {
  //   int beaconid = 5;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidPosContentNull() {
  //   int beaconid = 5;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNullContentNotEmpty() {
  //   Integer beaconid = null;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNullContentEmpty() {
  //   Integer beaconid = null;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNullContentNull() {
  //   Integer beaconid = null;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidMaxPosContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetBeaconidBeaconidMaxPosContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetBeaconidBeaconidMaxPosContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetBeaconidBeaconidMinNegContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetBeaconidBeaconidMinNegContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetBeaconidBeaconidMinNegContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }


  // @Test
  // void testGetContentBeaconidZeroContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(0, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidZeroContentEmpty() {
  //   String content = "";
  //   Command command = new Command(0, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidZeroContentNull() {
  //   String content = null;
  //   Command command = new Command(0, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNegContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(-5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNegContentEmpty() {
  //   String content = "";
  //   Command command = new Command(-5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNegContentNull() {
  //   String content = null;
  //   Command command = new Command(-5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidPosContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidPosContentEmpty() {
  //   String content = "";
  //   Command command = new Command(5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidPosContentNull() {
  //   String content = null;
  //   Command command = new Command(5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNullContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(null, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNullContentEmpty() {
  //   String content = "";
  //   Command command = new Command(null, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNullContentNull() {
  //   String content = null;
  //   Command command = new Command(null, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMaxPosContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMaxPosContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMaxPosContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMinNegContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMinNegContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMinNegContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNullContentNotEmpty() {
  //   Command command = new Command(null, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidZeroContentNotEmpty() {
  //   Command command = new Command(0, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidPosContentNotEmpty() {
  //   Command command = new Command(5, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNegContentNotEmpty() {
  //   Command command = new Command(-5, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMaxPosContentNotEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMinNegContentNotEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNullContentEmpty() {
  //   Command command = new Command(null, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidZeroContentEmpty() {
  //   Command command = new Command(0, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidPosContentEmpty() {
  //   Command command = new Command(5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNegContentEmpty() {
  //   Command command = new Command(-5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMaxPosContentEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMinNegContentEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNullContentNull() {
  //   Command command = new Command(null, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidZeroContentNull() {
  //   Command command = new Command(0, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidPosContentNull() {
  //   Command command = new Command(5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNegContentNull() {
  //   Command command = new Command(-5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMaxPosContentNull() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMinNegContentNull() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNullContentNotEmpty() {
  //   Command command = new Command(null, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidZeroContentNotEmpty() {
  //   Command command = new Command(0, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidPosContentNotEmpty() {
  //   Command command = new Command(5, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNegContentNotEmpty() {
  //   Command command = new Command(-5, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMaxPosContentNotEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMinNegContentNotEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNullContentEmpty() {
  //   Command command = new Command(null, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidZeroContentEmpty() {
  //   Command command = new Command(0, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidPosContentEmpty() {
  //   Command command = new Command(5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNegContentEmpty() {
  //   Command command = new Command(-5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMaxPosContentEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMinNegContentEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNullContentNull() {
  //   Command command = new Command(null, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidZeroContentNull() {
  //   Command command = new Command(0, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidPosContentNull() {
  //   Command command = new Command(5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNegContentNull() {
  //   Command command = new Command(-5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMaxPosContentNull() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMinNegContentNull() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }
}
