package com.reffians.c2.model;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.reffians.c2.model.Result;

@SpringBootTest
class ResultTest {
  @Test 
  void testResultConstructor(){
    int commandid = 0;
    String username = "username";
    String content = "content";
    Result result = new Result(commandid, username, content);
    assertEquals(commandid, result.getCommandid());
    assertEquals(username, result.getUsername());
    assertEquals(content, result.getContent());
    assertEquals(false, result.getHasBeenRead());
  }
  
  @Test 
  void testCommandSetters(){
    int commandid = 0;
    String username = "username";
    String content = "content";
    Result result = new Result(commandid, username, content);
    result.setResultRead();
    assertEquals(true, result.getHasBeenRead());
  }
}