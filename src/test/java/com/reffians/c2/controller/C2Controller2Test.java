package com.reffians.c2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.reffians.c2.dto.UserRequest;
import com.reffians.c2.service.BeaconService;
import com.reffians.c2.service.CommandService;
import com.reffians.c2.service.ResultService;
import com.reffians.c2.service.UserService;
import java.util.Collections;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class C2Controller2Test {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private BeaconService beaconService;
  @MockBean
  private CommandService commandService;
  @MockBean
  private ResultService resultService;
  @MockBean
  private UserService usrService;
  @MockBean
  private UserRequest userRequest;

  @Test
  void testLoginException() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "nikhil9");
    obj.put("password", "pw");
    String testUser = obj.toString();
    Mockito.when(userRequest.getAuthenticationToken()).thenThrow(new RuntimeException("runtime exception"));
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isInternalServerError());
  }

  @Test
  void testRegisterFail() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "nikhil9");
    obj.put("password", "pw");
    String testUser = obj.toString();
    Mockito.when(usrService.addUser("nikhil9", "pw")).thenThrow(new RuntimeException("runtime exception"));
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isInternalServerError());
  }

  @Test
  void beaconGiveResultNotValid() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(true);
    Mockito.when(commandService.getCommands(beaconid)).thenReturn(Collections.emptyList());
    // Mockito.when(commandService.updateCommandStatus(Mockito.anyList(),
    // Mockito.any(Command.Status.class))).thenReturn(Collections.emptyList());
    mockMvc.perform(post("/beacon/result")
        .contentType(MediaType.APPLICATION_JSON)
        //.content("{\"beacon\": {\"id\": 1, \"token\": \"token\"},\"status\": \"all\"}")
        .content("{'beacon': {'id': '1', 'token': token'}, 'results': [{'commandid': 3, 'content': 'asheets-mbp-2.lan\n', 'exec_time': '2022-12-05T00:39:33'}]")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void beaconGiveResultValid() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(true);
    Mockito.when(beaconService.getUserForBeacon(beaconid)).thenReturn("user");
    Mockito.when(commandService.getCommands(beaconid)).thenReturn(Collections.emptyList());
    Mockito.when(resultService.addResult(3, "user", "c")).thenReturn(null);
    // Mockito.when(commandService.updateCommandStatus(Mockito.anyList(),
    // Mockito.any(Command.Status.class))).thenReturn(Collections.emptyList());
    mockMvc.perform(post("/beacon/result")
        .contentType(MediaType.APPLICATION_JSON)
        //.content("{\"beacon\": {\"id\": 1, \"token\": \"token\"},\"status\": \"all\"}")
        .content("{'beacon': {'id': '1', 'token': token'}, 'results': [{'commandid': 3, 'content': 'c', 'exec_time': '2022-12-05T00:39:33'}]")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  void userResultsNotValid() throws Exception {
    mockMvc.perform(post("/user/result")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
  }

  // @Test
  // @WithMockUser
  // void userResultsValid() throws Exception {
  //   mockMvc.perform(post("/user/result")
  //     .contentType(MediaType.APPLICATION_JSON))
  //     .andExpect(status().isBadRequest());
  // }

}