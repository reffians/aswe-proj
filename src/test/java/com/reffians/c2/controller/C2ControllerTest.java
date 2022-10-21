package com.reffians.c2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}