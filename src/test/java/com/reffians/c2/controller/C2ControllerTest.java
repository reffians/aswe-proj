package com.reffians.c2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.reffians.c2.model.commands.Command;
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
public class C2ControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private BeaconService beaconService;
  @MockBean
  private CommandService commandService;


  @Test
  private void testReceiveCommandValidBeaconExistsStatusAll() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(true);
    Mockito.when(commandService.getCommands(beaconid)).thenReturn(Collections.emptyList());
    Mockito.when(commandService.updateCommandStatus(Mockito.anyList(),
        Mockito.any(Command.Status.class))).thenReturn(Collections.emptyList());
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"},\"status\": \"all\"}")
        .queryParam("beaconid", "5"))
        .andExpect(status().isOk());
  }


  @Test
  private void testReceiveCommandValidBeaconExistsStatusPending() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(true);
    Mockito.when(commandService.getCommands(beaconid)).thenReturn(Collections.emptyList());
    Mockito.when(commandService.updateCommandStatus(Mockito.anyList(),
        Mockito.any(Command.Status.class))).thenReturn(Collections.emptyList());
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"},\"status\": \"pending\"}")
        .queryParam("beaconid", "5"))
        .andExpect(status().isOk());
  }


  @Test
  private void testReceiveCommandValidBeaconNExists() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(false);
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"},\"status\": \"all\"}"))
        .andExpect(status().isBadRequest());
  }


  @Test
  private void testReceiveCommandInvalidStatus() throws Exception {
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"},\"status\": \"invalid\"}")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }


  @Test
  private void testReceiveCommandEmptyStatus() throws Exception {
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"}, \"status\": \"\"}")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }


  @Test
  private void testReceiveCommandMissingStatus() throws Exception {
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"token\"}}")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }


  @Test
  private void testReceiveCommandEmptyToken() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(false);
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1, \"token\": \"\"},\"status\": \"all\"}"))
        .andExpect(status().isBadRequest());
  }


  @Test
  private void testReceiveCommandMissingToken() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(false);
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"id\": 1},\"status\": \"all\"}"))
        .andExpect(status().isBadRequest());
  }


  @Test
  private void testReceiveCommandMissingBeaconId() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(false);
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"beacon\": {\"token\": \"token\"}, \"status\": \"all\"}"))
        .andExpect(status().isBadRequest());
  }


  @Test
  private void testReceiveCommandMissingBeacon() throws Exception {
    Integer beaconid = 1;
    Mockito.when(beaconService.beaconExists(beaconid, "token")).thenReturn(false);
    mockMvc.perform(post("/beacon/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"status\": \"all\"}"))
        .andExpect(status().isBadRequest());
  }


  //Test registration
  @Test
  private void testRegister() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil1");
	  obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isOk());
  }
 
  @Test
  private void testRegisterOutofOrder() throws Exception {
    JSONObject obj = new JSONObject();
	  obj.put("password", "pword");
    obj.put("username", "Nikhil2");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isOk());
  }

  @Test
  private void testRegisterNoUsername() throws Exception {
    JSONObject obj = new JSONObject();
	  obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  private void testRegisterNoPassword() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil1");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  private void testRegisterEmptyJson() throws Exception {
    JSONObject obj = new JSONObject();
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  private void testRegisterJunkJson() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("asdfasd", "asdfasdfa");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  //login tests
  @Test
  private void testLogin() throws Exception {
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
  private void testLoginFailureNoUser() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil4");
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isUnauthorized());
  }

  @Test
  private void testLoginFailureWrongPassword() throws Exception {
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
  private void testLoginOutofOrder() throws Exception {
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
  private void testLoginNoPassword() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil6");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  private void testLoginNoUsername() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  private void testLoginJunk() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("uasdfadsf", "Nikhil6");
    obj.put("sedasdf", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }
}