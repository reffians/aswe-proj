package com.reffians.c2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.reffians.c2.model.Command.Status;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommandTest {
  // @Test
  // public void testGetBeaconidBeaconidZeroContentNotEmpty() {
  //   int beaconid = 0;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidZeroContentEmpty() {
  //   int beaconid = 0;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidZeroContentNull() {
  //   int beaconid = 0;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidNegContentNotEmpty() {
  //   int beaconid = -10;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidNegContentEmpty() {
  //   int beaconid = -10;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidNegContentNull() {
  //   int beaconid = -10;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidPosContentNotEmpty() {
  //   int beaconid = 5;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidPosContentEmpty() {
  //   int beaconid = 5;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidPosContentNull() {
  //   int beaconid = 5;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidNullContentNotEmpty() {
  //   Integer beaconid = null;
  //   Command command = new Command(beaconid, "foo");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidNullContentEmpty() {
  //   Integer beaconid = null;
  //   Command command = new Command(beaconid, "");
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidNullContentNull() {
  //   Integer beaconid = null;
  //   Command command = new Command(beaconid, null);
  //   assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // public void testGetBeaconidBeaconidMaxPosContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetBeaconidBeaconidMaxPosContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetBeaconidBeaconidMaxPosContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetBeaconidBeaconidMinNegContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetBeaconidBeaconidMinNegContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetBeaconidBeaconidMinNegContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }


  // @Test
  // public void testGetContentBeaconidZeroContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(0, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidZeroContentEmpty() {
  //   String content = "";
  //   Command command = new Command(0, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidZeroContentNull() {
  //   String content = null;
  //   Command command = new Command(0, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidNegContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(-5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidNegContentEmpty() {
  //   String content = "";
  //   Command command = new Command(-5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidNegContentNull() {
  //   String content = null;
  //   Command command = new Command(-5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidPosContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidPosContentEmpty() {
  //   String content = "";
  //   Command command = new Command(5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidPosContentNull() {
  //   String content = null;
  //   Command command = new Command(5, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidNullContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(null, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidNullContentEmpty() {
  //   String content = "";
  //   Command command = new Command(null, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidNullContentNull() {
  //   String content = null;
  //   Command command = new Command(null, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidMaxPosContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidMaxPosContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidMaxPosContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MAX_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidMinNegContentNonEmpty() {
  //   String content = "foo";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidMinNegContentEmpty() {
  //   String content = "";
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testGetContentBeaconidMinNegContentNull() {
  //   String content = null;
  //   Command command = new Command(Integer.MIN_VALUE, content);
  //   assertEquals(content, command.getContent());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidNullContentNotEmpty() {
  //   Command command = new Command(null, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidZeroContentNotEmpty() {
  //   Command command = new Command(0, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidPosContentNotEmpty() {
  //   Command command = new Command(5, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidNegContentNotEmpty() {
  //   Command command = new Command(-5, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidMaxPosContentNotEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidMinNegContentNotEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "foo");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidNullContentEmpty() {
  //   Command command = new Command(null, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidZeroContentEmpty() {
  //   Command command = new Command(0, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidPosContentEmpty() {
  //   Command command = new Command(5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidNegContentEmpty() {
  //   Command command = new Command(-5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidMaxPosContentEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidMinNegContentEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidNullContentNull() {
  //   Command command = new Command(null, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidZeroContentNull() {
  //   Command command = new Command(0, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidPosContentNull() {
  //   Command command = new Command(5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidNegContentNull() {
  //   Command command = new Command(-5, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidMaxPosContentNull() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testNewCommandStatusBeaconidMinNegContentNull() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidNullContentNotEmpty() {
  //   Command command = new Command(null, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidZeroContentNotEmpty() {
  //   Command command = new Command(0, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidPosContentNotEmpty() {
  //   Command command = new Command(5, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidNegContentNotEmpty() {
  //   Command command = new Command(-5, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidMaxPosContentNotEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidMinNegContentNotEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "foo");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidNullContentEmpty() {
  //   Command command = new Command(null, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidZeroContentEmpty() {
  //   Command command = new Command(0, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidPosContentEmpty() {
  //   Command command = new Command(5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidNegContentEmpty() {
  //   Command command = new Command(-5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidMaxPosContentEmpty() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidMinNegContentEmpty() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidNullContentNull() {
  //   Command command = new Command(null, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidZeroContentNull() {
  //   Command command = new Command(0, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidPosContentNull() {
  //   Command command = new Command(5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidNegContentNull() {
  //   Command command = new Command(-5, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidMaxPosContentNull() {
  //   Command command = new Command(Integer.MAX_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // public void testSetStatusBeaconidMinNegContentNull() {
  //   Command command = new Command(Integer.MIN_VALUE, "");
  //   command.setStatus(Status.sent);
  //   assertEquals(Status.sent, command.getStatus());
  // }
}
