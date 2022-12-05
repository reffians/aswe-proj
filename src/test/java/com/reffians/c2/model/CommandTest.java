package com.reffians.c2.model;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.reffians.c2.model.commands.*;
import com.reffians.c2.exception.*;

@SpringBootTest
class CommandTest {

  // Command tests
  @Test 
  void testCommandConstructor(){
    int beaconid = 0;
    Command comm = new Command(beaconid);
    assertEquals(beaconid, comm.getBeaconid());
    assertEquals(false, comm.getHasBeenSent());
  }
  
  @Test 
  void testCommandSetters(){
    int beaconid = 0;
    Command comm = new Command(beaconid);
    comm.setCommandSent();
    assertEquals(true, comm.getHasBeenSent());
    comm.setType("TYPE");
    assertEquals("TYPE", comm.getType());
    try{
      comm.setCommandContent("content");
      assertEquals("content", comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  // DownloadCommand tests
  @Test
  void testDownloadCommandConstructor(){
    int beaconid = 0;
    String content = "https://www.google.com/";
    try{
      DownloadCommand comm = new DownloadCommand(beaconid, content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("DOWNLOAD", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testDownloadCommandConstructorBadContent(){
    int beaconid = 0;
    String content = "badurl";
    try{
      DownloadCommand comm = new DownloadCommand(beaconid, content);
    } catch (CommandContentMismatchException e){
      assertEquals(e.getMessage(), String.format("Content %s does not match command type %s.", content, "DOWNLOAD"));
    }
  }

  @Test
  void testDownloadCommandConstructorBadContent2(){
    int beaconid = 0;
    String content = "https://www.googl/";
    try{
      DownloadCommand comm = new DownloadCommand(beaconid, content);
    } catch (CommandContentMismatchException e){
      assertEquals(e.getMessage(), String.format("Content %s does not match command type %s.", content, "DOWNLOAD"));
    }
  }

  // ExecuteCommand tests
  @Test
  void testExecuteCommandConstructor(){
    int beaconid = 0;
    String content = "filename.txt";
    try{
      ExecuteCommand comm = new ExecuteCommand(beaconid, content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("EXECUTE", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testExecuteCommandConstructor2(){
    int beaconid = 0;
    String content = "filename";
    try{
      ExecuteCommand comm = new ExecuteCommand(beaconid, content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("EXECUTE", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testExecuteCommandConstructor3(){
    int beaconid = 0;
    String content = "/file/path/to/file.txt";
    try{
      ExecuteCommand comm = new ExecuteCommand(beaconid, content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("EXECUTE", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testExecuteCommandConstructorBadContent(){
    int beaconid = 0;
    String content = "badfilename''^*";
    try{
      ExecuteCommand comm = new ExecuteCommand(beaconid, content);
    } catch (CommandContentMismatchException e){
      assertEquals(e.getMessage(), String.format("Content %s does not match command type %s.", content, "EXECUTE"));
    }
  }

  // GetHostNameCommand Tests

  @Test
  void testGetHostNameCommandConstructor(){
    int beaconid = 0;
    String content = "";
    try{
      GetHostNameCommand comm = new GetHostNameCommand(beaconid, content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("GETHOSTNAME", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testGetHostNameCommandConstructorBadContent(){
    int beaconid = 0;
    String content = "anycontentisbadcontent";
    try{
      GetHostNameCommand comm = new GetHostNameCommand(beaconid, content);
    } catch (CommandContentMismatchException e){
      assertEquals(e.getMessage(), String.format("Content %s does not match command type %s.", content, "GETHOSTNAME"));
    }
  }

  // GetHostOsCommand Tests

  @Test
  void testGetHostOsCommandConstructor(){
    int beaconid = 0;
    String content = "";
    try{
      GetHostOsCommand comm = new GetHostOsCommand(beaconid, content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("GETHOSTOS", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testGetHostOsCommandConstructorBadContent(){
    int beaconid = 0;
    String content = "anycontentisbadcontent";
    try{
      GetHostOsCommand comm = new GetHostOsCommand(beaconid, content);
    } catch (CommandContentMismatchException e){
      assertEquals(e.getMessage(), String.format("Content %s does not match command type %s.", content, "GETHOSTOS"));
    }
  }

    // GetSleepCommand Tests

  @Test
  void testGetSleepCommandConstructor(){
    int beaconid = 0;
    String content = "300";
    try{
      SleepCommand comm = new SleepCommand(beaconid, content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("SLEEP", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testGetSleepCommandConstructorBadContent(){
    int beaconid = 0;
    String content = "-100";
    try{
      SleepCommand comm = new SleepCommand(beaconid, content);
    } catch (CommandContentMismatchException e){
      assertEquals(e.getMessage(), String.format("Content %s does not match command type %s.", content, "SLEEP"));
    }
  }

  @Test
  void testGetSleepCommandConstructorBadContent2(){
    int beaconid = 0;
    String content = "stringcontent";
    try{
      SleepCommand comm = new SleepCommand(beaconid, content);
    } catch (CommandContentMismatchException e){
      assertEquals(e.getMessage(), String.format("Content %s does not match command type %s.", content, "SLEEP"));
    }
  }

    // GetStop Tests

  @Test
  void testGetStopCommandConstructor(){
    int beaconid = 0;
    String content = "";
    try{
      StopCommand comm = new StopCommand(beaconid, content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("STOP", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testGetStopCommandConstructorBadContent(){
    int beaconid = 0;
    String content = "anycontentisbadcontent";
    try{
      StopCommand comm = new StopCommand(beaconid, content);
    } catch (CommandContentMismatchException e){
      assertEquals(e.getMessage(), String.format("Content %s does not match command type %s.", content, "STOP"));
    }
  }



  // @Test
  // void testGetBeaconidBeaconidZeroContentNotEmpty() {
  // int beaconid = 0;
  // Command command = new Command(beaconid, "foo");
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidZeroContentEmpty() {
  // int beaconid = 0;
  // Command command = new Command(beaconid, "");
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidZeroContentNull() {
  // int beaconid = 0;
  // Command command = new Command(beaconid, null);
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNegContentNotEmpty() {
  // int beaconid = -10;
  // Command command = new Command(beaconid, "foo");
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNegContentEmpty() {
  // int beaconid = -10;
  // Command command = new Command(beaconid, "");
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNegContentNull() {
  // int beaconid = -10;
  // Command command = new Command(beaconid, null);
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidPosContentNotEmpty() {
  // int beaconid = 5;
  // Command command = new Command(beaconid, "foo");
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidPosContentEmpty() {
  // int beaconid = 5;
  // Command command = new Command(beaconid, "");
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidPosContentNull() {
  // int beaconid = 5;
  // Command command = new Command(beaconid, null);
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNullContentNotEmpty() {
  // Integer beaconid = null;
  // Command command = new Command(beaconid, "foo");
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNullContentEmpty() {
  // Integer beaconid = null;
  // Command command = new Command(beaconid, "");
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidNullContentNull() {
  // Integer beaconid = null;
  // Command command = new Command(beaconid, null);
  // assertEquals(beaconid, command.getBeaconid());
  // }

  // @Test
  // void testGetBeaconidBeaconidMaxPosContentNonEmpty() {
  // String content = "foo";
  // Command command = new Command(Integer.MAX_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetBeaconidBeaconidMaxPosContentEmpty() {
  // String content = "";
  // Command command = new Command(Integer.MAX_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetBeaconidBeaconidMaxPosContentNull() {
  // String content = null;
  // Command command = new Command(Integer.MAX_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetBeaconidBeaconidMinNegContentNonEmpty() {
  // String content = "foo";
  // Command command = new Command(Integer.MIN_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetBeaconidBeaconidMinNegContentEmpty() {
  // String content = "";
  // Command command = new Command(Integer.MIN_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetBeaconidBeaconidMinNegContentNull() {
  // String content = null;
  // Command command = new Command(Integer.MIN_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidZeroContentNonEmpty() {
  // String content = "foo";
  // Command command = new Command(0, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidZeroContentEmpty() {
  // String content = "";
  // Command command = new Command(0, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidZeroContentNull() {
  // String content = null;
  // Command command = new Command(0, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNegContentNonEmpty() {
  // String content = "foo";
  // Command command = new Command(-5, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNegContentEmpty() {
  // String content = "";
  // Command command = new Command(-5, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNegContentNull() {
  // String content = null;
  // Command command = new Command(-5, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidPosContentNonEmpty() {
  // String content = "foo";
  // Command command = new Command(5, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidPosContentEmpty() {
  // String content = "";
  // Command command = new Command(5, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidPosContentNull() {
  // String content = null;
  // Command command = new Command(5, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNullContentNonEmpty() {
  // String content = "foo";
  // Command command = new Command(null, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNullContentEmpty() {
  // String content = "";
  // Command command = new Command(null, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidNullContentNull() {
  // String content = null;
  // Command command = new Command(null, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMaxPosContentNonEmpty() {
  // String content = "foo";
  // Command command = new Command(Integer.MAX_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMaxPosContentEmpty() {
  // String content = "";
  // Command command = new Command(Integer.MAX_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMaxPosContentNull() {
  // String content = null;
  // Command command = new Command(Integer.MAX_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMinNegContentNonEmpty() {
  // String content = "foo";
  // Command command = new Command(Integer.MIN_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMinNegContentEmpty() {
  // String content = "";
  // Command command = new Command(Integer.MIN_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testGetContentBeaconidMinNegContentNull() {
  // String content = null;
  // Command command = new Command(Integer.MIN_VALUE, content);
  // assertEquals(content, command.getContent());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNullContentNotEmpty() {
  // Command command = new Command(null, "foo");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidZeroContentNotEmpty() {
  // Command command = new Command(0, "foo");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidPosContentNotEmpty() {
  // Command command = new Command(5, "foo");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNegContentNotEmpty() {
  // Command command = new Command(-5, "foo");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMaxPosContentNotEmpty() {
  // Command command = new Command(Integer.MAX_VALUE, "foo");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMinNegContentNotEmpty() {
  // Command command = new Command(Integer.MIN_VALUE, "foo");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNullContentEmpty() {
  // Command command = new Command(null, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidZeroContentEmpty() {
  // Command command = new Command(0, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidPosContentEmpty() {
  // Command command = new Command(5, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNegContentEmpty() {
  // Command command = new Command(-5, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMaxPosContentEmpty() {
  // Command command = new Command(Integer.MAX_VALUE, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMinNegContentEmpty() {
  // Command command = new Command(Integer.MIN_VALUE, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNullContentNull() {
  // Command command = new Command(null, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidZeroContentNull() {
  // Command command = new Command(0, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidPosContentNull() {
  // Command command = new Command(5, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidNegContentNull() {
  // Command command = new Command(-5, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMaxPosContentNull() {
  // Command command = new Command(Integer.MAX_VALUE, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testNewCommandStatusBeaconidMinNegContentNull() {
  // Command command = new Command(Integer.MIN_VALUE, "");
  // assertEquals(Status.pending, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNullContentNotEmpty() {
  // Command command = new Command(null, "foo");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidZeroContentNotEmpty() {
  // Command command = new Command(0, "foo");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidPosContentNotEmpty() {
  // Command command = new Command(5, "foo");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNegContentNotEmpty() {
  // Command command = new Command(-5, "foo");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMaxPosContentNotEmpty() {
  // Command command = new Command(Integer.MAX_VALUE, "foo");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMinNegContentNotEmpty() {
  // Command command = new Command(Integer.MIN_VALUE, "foo");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNullContentEmpty() {
  // Command command = new Command(null, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidZeroContentEmpty() {
  // Command command = new Command(0, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidPosContentEmpty() {
  // Command command = new Command(5, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNegContentEmpty() {
  // Command command = new Command(-5, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMaxPosContentEmpty() {
  // Command command = new Command(Integer.MAX_VALUE, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMinNegContentEmpty() {
  // Command command = new Command(Integer.MIN_VALUE, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNullContentNull() {
  // Command command = new Command(null, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidZeroContentNull() {
  // Command command = new Command(0, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidPosContentNull() {
  // Command command = new Command(5, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidNegContentNull() {
  // Command command = new Command(-5, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMaxPosContentNull() {
  // Command command = new Command(Integer.MAX_VALUE, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }

  // @Test
  // void testSetStatusBeaconidMinNegContentNull() {
  // Command command = new Command(Integer.MIN_VALUE, "");
  // command.setStatus(Status.sent);
  // assertEquals(Status.sent, command.getStatus());
  // }
}
