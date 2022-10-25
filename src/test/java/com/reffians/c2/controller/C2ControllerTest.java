package com.reffians.c2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class C2ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetCommandBeaconBeaconidPosNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "5"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidPosStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "5")
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidPosStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "5")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidPosStatusNonEmptyInvalid() throws Exception {
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
  public void testGetCommandBeaconBeaconidZeroStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "-5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "-5")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "-5")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegStatusNonEmptyInvalid() throws Exception {
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
  public void testGetCommandBeaconBeaconidEmptyStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPosNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPosStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE))
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPosStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE))
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPosStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE))
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE))
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE))
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE))
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphNumNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphNumStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphNumStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphNumStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidHexNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidHexStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidHexStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidHexStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidPosStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", "5"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidPosStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidStatusNonEmptyInvalidOutOfOrder() throws Exception {
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
  public void testGetCommandBeaconBeaconidZeroStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", "0"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", "-5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", "-5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidNegStatusNonEmptyInvalidOutOfOrder() throws Exception {
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
  public void testGetCommandBeaconBeaconidEmptyStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPosStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPosStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMaxPosStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidMinNegStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphNoStatusOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphNumNoStatusOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphNumStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphNumStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidAlphNumStatusNEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidHexNoStatusOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidHexStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetCommandBeaconBeaconidHexStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconBeaconidHexStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
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

  @Test
  public void testGetCommandBeaconBadUsername() throws Exception {
    mockMvc.perform(get("/beacon/create")
        .queryParam("username", "bad_username"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testGetCommandBeaconGoodUsername() throws Exception {
    mockMvc.perform(get("/beacon/create")
        .queryParam("username", "username"))
        .andExpect(status().isOk());
  }


  @Test
  public void testGetCreateBeaconNoParams() throws Exception {
    mockMvc.perform(get("/beacon/create"))
        .andExpect(status().isBadRequest());
  }
}