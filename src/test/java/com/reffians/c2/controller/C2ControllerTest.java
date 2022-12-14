package com.reffians.c2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.reffians.c2.service.BeaconService;
import com.reffians.c2.service.CommandService;
import java.util.Collections;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class C2ControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private BeaconService beaconService;
  @MockBean
  private CommandService commandService;

  @Test
  void testReceiveCommandValidBeaconExistsStatusAll() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(true);
    Mockito.when(commandService.getCommands(beaconid)).thenReturn(Collections.emptyList());
    // Mockito.when(commandService.updateCommandStatus(Mockito.anyList(),
    // Mockito.any(Command.Status.class))).thenReturn(Collections.emptyList());
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"},\"status\": \"all\"}")
        .queryParam("beaconid", "5"))
        .andExpect(status().isOk());
  }

  @Test
  void testReceiveCommandValidBeaconExistsStatusPending() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(true);
    Mockito.when(commandService.getCommands(beaconid)).thenReturn(Collections.emptyList());
    // Mockito.when(commandService.updateCommandStatus(Mockito.anyList(),
    // Mockito.any(Command.Status.class))).thenReturn(Collections.emptyList());
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"},\"status\": \"pending\"}")
        .queryParam("beaconid", "5"))
        .andExpect(status().isOk());
  }

  @Test
  void testReceiveCommandValidBeaconNExists() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(false);
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"}}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testReceiveCommandInvalidStatus() throws Exception {
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"}}")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testReceiveCommandEmptyStatus() throws Exception {
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"}, \"status\": \"\"}")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testReceiveCommandMissingStatus() throws Exception {
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"}}")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testReceiveCommandEmptyToken() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(false);
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"\"},\"status\": \"all\"}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testReceiveCommandMissingToken() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(false);
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1},\"status\": \"all\"}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testReceiveCommandMissingBeaconId() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(false);
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"token\": \"token\"}, \"status\": \"all\"}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testReceiveCommandMissingBeacon() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(false);
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"status\": \"all\"}"))
        .andExpect(status().isBadRequest());
  }

  // Test registration
  @Test
  void testRegister() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil1");
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isOk());
  }

  @Test
  void testRegisterUserExists() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil3");
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
    JSONObject obj2 = new JSONObject();
    obj2.put("username", "Nikhil3");
    obj2.put("password", "pword");
    String testUser2 = obj2.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser2))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testRegisterOutofOrder() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("password", "pword");
    obj.put("username", "Nikhil2");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isOk());
  }

  @Test
  void testRegisterNoUsername() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testRegisterNoPassword() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil1");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testRegisterEmptyJson() throws Exception {
    JSONObject obj = new JSONObject();
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testRegisterJunkJson() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("asdfasd", "asdfasdfa");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  // login tests
  @Test
  void testLogin() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil3");
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isOk());
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isOk());
  }

  @Test
  void testLoginFailureNoUser() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil4");
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void testLoginFailureWrongPassword() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil5");
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isOk());
    JSONObject obj2 = new JSONObject();
    obj2.put("username", "Nikhil5");
    obj2.put("password", "pword1");
    String testUser2 = obj2.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser2))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void testLoginOutofOrder() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil6");
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isOk());
    JSONObject obj2 = new JSONObject();
    obj2.put("password", "pword");
    obj2.put("username", "Nikhil6");
    String testUser2 = obj2.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser2))
        .andExpect(status().isOk());
  }

  @Test
  void testLoginNoPassword() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil6");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testLoginNoUsername() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  void testLoginJunk() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("uasdfadsf", "Nikhil6");
    obj.put("sedasdf", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }
}