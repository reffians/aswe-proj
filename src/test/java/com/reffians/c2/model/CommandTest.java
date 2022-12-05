package com.reffians.c2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.reffians.c2.model.commands.*;
import com.reffians.c2.exception.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

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
  void testSleepCommandConstructor(){
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
  void testSleepCommandConstructorBadContent(){
    int beaconid = 0;
    String content = "-100";
    try{
      SleepCommand comm = new SleepCommand(beaconid, content);
    } catch (CommandContentMismatchException e){
      assertEquals(e.getMessage(), String.format("Content %s does not match command type %s.", content, "SLEEP"));
    }
  }

  @Test
  void testSleepCommandConstructorBadContent2(){
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
  void testStopCommandConstructor(){
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
  void testStopCommandConstructorBadContent(){
    int beaconid = 0;
    String content = "anycontentisbadcontent";
    try{
      StopCommand comm = new StopCommand(beaconid, content);
    } catch (CommandContentMismatchException e){
      assertEquals(e.getMessage(), String.format("Content %s does not match command type %s.", content, "STOP"));
    }
  }

  // test command factory

  @Test
  void testCommandFactoryNullID(){
    try{
      Command comm = CommandFactory.getCommand(null, "type", "content");
    } catch(Exception e){
      assertEquals(e.getClass().getName(), "java.lang.IllegalArgumentException");
    }
  }
  @Test
  void testCommandFactoryEmptyType(){
    int beaconid=0;
    try{
      Command comm = CommandFactory.getCommand(beaconid, "", "content");
    } catch(Exception e){
      assertEquals(e.getClass().getName(), "java.lang.IllegalArgumentException");
    }
  }
  @Test
  void testCommandFactoryBadType(){
    int beaconid=0;
    try{
      Command comm = CommandFactory.getCommand(beaconid, "BADTYPE", "content");
    } catch(Exception e){
      assertEquals(e.getClass().getName(), "java.lang.IllegalArgumentException");
    }
  }

 @Test
  void testCommandFactoryStop(){
    int beaconid = 0;
    String content = "";
    try{
      Command comm = CommandFactory.getCommand(beaconid, "STOP", content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("STOP", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

   @Test
  void testCommandFactoryStopBad(){
    int beaconid = 0;
    String content = "anycontentisbadcontent";
    try{
      Command comm = CommandFactory.getCommand(beaconid, "STOP", content);
    } catch (CommandContentMismatchException e){
      assertEquals(e.getMessage(), String.format("Content %s does not match command type %s.", content, "STOP"));
    }
  }

  @Test
  void testCommandFactorySleep(){
    int beaconid = 0;
    String content = "300";
    try{
      Command comm = CommandFactory.getCommand(beaconid, "SLEEP", content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("SLEEP", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testCommandFactoryExecute(){
    int beaconid = 0;
    String content = "filename.txt";
    try{
      Command comm = CommandFactory.getCommand(beaconid, "EXECUTE", content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("EXECUTE", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testCommandFactoryDownload(){
    int beaconid = 0;
    String content = "https://www.google.com/";
    try{
      Command comm = CommandFactory.getCommand(beaconid, "DOWNLOAD", content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("DOWNLOAD", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testCommandFactoryGetHostName(){
    int beaconid = 0;
    String content = "";
    try{
      Command comm = CommandFactory.getCommand(beaconid, "GETHOSTNAME", content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("GETHOSTNAME", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

  @Test
  void testCommandFactoryGetHostOs(){
    int beaconid = 0;
    String content = "";
    try{
      Command comm = CommandFactory.getCommand(beaconid, "GETHOSTOS", content);
      assertEquals(beaconid, comm.getBeaconid());
      assertEquals("GETHOSTOS", comm.getType());
      assertEquals(content, comm.getContent());
    } catch (Exception e){
      fail("Exception should not have been thrown: " + e.getMessage());
    }
  }

}
