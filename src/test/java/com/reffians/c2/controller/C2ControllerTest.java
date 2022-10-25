package com.reffians.c2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
public class C2ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetCommandBeaconValidBeaconIDNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
    .queryParam("beaconid", "123456789"))
    .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconValidBeaconIDValidStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
    .queryParam("beaconid", "123456789")
    .queryParam("status", "pending"))
    .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconValidBeaconIDInvalidStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
    .queryParam("beaconid", "123456789")
    .queryParam("status", "foo"))
    .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconNoParams() throws Exception {
    mockMvc.perform(get("/beacon/command"))
    .andExpect(status().isBadRequest());
  }

  @Test
  public void testRegister() throws Exception {
	JSONObject obj = new JSONObject();
	obj.put("username", "Nikhil1");
	obj.put("password", "pword");
	String testUser = obj.toString();
	mockMvc.perform(MockMvcRequestBuilders.post("/register")
	.contentType(MediaType.APPLICATION_JSON).content(testUser))
	.andExpect(status().isOk());
  }

  @Test
  public void testLogin() throws Exception {
	JSONObject obj = new JSONObject();
	obj.put("username", "Nikhil1");
	obj.put("password", "pword");
	String testUser = obj.toString();
	mockMvc.perform(MockMvcRequestBuilders.post("/login")
	.contentType(MediaType.APPLICATION_JSON).content(testUser))
	.andExpect(status().isOk());
  }

}