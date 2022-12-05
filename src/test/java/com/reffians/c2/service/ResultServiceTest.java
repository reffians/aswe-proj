package com.reffians.c2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.reffians.c2.model.Result;
import com.reffians.c2.repository.ResultRepository;

@SpringBootTest
class ResultServiceTest {
  @Autowired
  private ResultService resultService;

  @MockBean
  private ResultRepository resultRepository;
  //TODO: test getNotReceivedResults

  //test addCommand
  // @Test
  // void testAddResult() {
  //   int commandid = 0;
  //   String username = "username";
  //   String content = "content";

  //   Result result = new Result(commandid, username, result);
  //   Mockito.when(resultRepository.save(any(Result.class))).thenReturn(result);
  //   assertEquals(result, resultService.addResult(commandid, username, content));
  // }
}
