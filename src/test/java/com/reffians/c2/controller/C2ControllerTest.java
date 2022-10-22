package com.reffians.c2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class C2ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetCommandBeaconBeaconidPositiveNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "5"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidPositiveStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "5")
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidPositiveStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "5")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "5")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidZeroNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidZeroStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0")
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidZeroStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidZeroStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegativeNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "-5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegativeStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "-5")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegativeStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "-5")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidNegativeStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "-5")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidEmptyNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidEmptyStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidEmptyStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidEmptyStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPositiveNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPositiveStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE))
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPositiveStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE))
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidMaxPositiveStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE))
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegativeNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegativeStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE))
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegativeStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE))
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidMinNegativeStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE))
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidPositiveStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", "5"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidPositiveStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidZeroStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", "0"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidZeroStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", "0"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidZeroStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", "0"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegativeStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", "-5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegativeStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", "-5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidNegativeStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", "-5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidEmptyStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidEmptyStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidEmptyStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPositiveStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPositiveStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidMaxPositiveStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegativeStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegativeStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconValidBeaconidMinNegativeStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconNoParams() throws Exception {
    mockMvc.perform(get("/beacon/command"))
        .andExpect(status().isBadRequest());
  }
}