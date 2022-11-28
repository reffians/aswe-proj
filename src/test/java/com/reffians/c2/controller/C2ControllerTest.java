package com.reffians.c2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class C2ControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidPosNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "5"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidPosStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "5")
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidPosStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "5")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidPosStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "5")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidZeroNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidZeroStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0")
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidZeroStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidZeroStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidNegNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "-5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidNegStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "-5")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidNegStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "-5")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidNegStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "-5")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidEmptyNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidEmptyStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidEmptyStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidEmptyStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMaxPosNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMaxPosStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE))
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMaxPosStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE))
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMaxPosStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE))
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMinNegNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMinNegStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE))
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMinNegStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE))
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMinNegStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE))
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphNumNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphNumStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphNumStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphNumStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidHexNoStatus() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidHexStatusNonEmptyValid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidHexStatusEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidHexStatusNonEmptyInvalid() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidPosStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", "5"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidPosStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", "5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidZeroStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", "0"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidZeroStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", "0"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidZeroStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", "0"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidNegStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", "-5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidNegStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", "-5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidNegStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", "-5"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidEmptyStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidEmptyStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidEmptyStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMaxPosStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMaxPosStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMaxPosStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", Integer.toString(Integer.MAX_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMinNegStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "pending")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMinNegStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidMinNegStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("status", "foo")
        .queryParam("beaconid", Integer.toString(Integer.MIN_VALUE)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphNoStatusOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "abcd")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphNumNoStatusOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphNumStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", "pending"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphNumStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidAlphNumStatusNEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "123abcd")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidHexNoStatusOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidHexStatusNonEmptyValidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
        .queryParam("status", "pending"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidHexStatusEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
        .queryParam("status", ""))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconBeaconidHexStatusNonEmptyInvalidOutOfOrder() throws Exception {
    mockMvc.perform(get("/beacon/command")
        .queryParam("beaconid", "0xbeef")
        .queryParam("status", "foo"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testGetCommandBeaconNoParams() throws Exception {
    mockMvc.perform(get("/beacon/command"))
        .andExpect(status().isBadRequest());
  }

  //Test registration
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
  public void testRegisterOutofOrder() throws Exception {
    JSONObject obj = new JSONObject();
	  obj.put("password", "pword");
    obj.put("username", "Nikhil2");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isOk());
  }

  @Test
  public void testRegisterNoUsername() throws Exception {
    JSONObject obj = new JSONObject();
	  obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testRegisterNoPassword() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil1");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testRegisterEmptyJson() throws Exception {
    JSONObject obj = new JSONObject();
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testRegisterJunkJson() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("asdfasd", "asdfasdfa");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/register")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  //login tests
  @Test
  public void testLogin() throws Exception {
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
  public void testLoginFailureNoUser() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil4");
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void testLoginFailureWrongPassword() throws Exception {
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
  public void testLoginOutofOrder() throws Exception {
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
  public void testLoginNoPassword() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("username", "Nikhil6");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testLoginNoUsername() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("password", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testLoginJunk() throws Exception {
    JSONObject obj = new JSONObject();
    obj.put("uasdfadsf", "Nikhil6");
    obj.put("sedasdf", "pword");
    String testUser = obj.toString();
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
        .contentType(MediaType.APPLICATION_JSON).content(testUser))
        .andExpect(status().isBadRequest());
  }

  @Test
  @WithMockUser
  public void testSubmitCommands() throws Exception {
    String testCommandContents = "[\"command1\", \"command2\"]\"";
    mockMvc.perform(MockMvcRequestBuilders.post("/user/command")
        .contentType(MediaType.APPLICATION_JSON)
        .content(testCommandContents)
        .queryParam("beaconid", "123456789"))
        .andExpect(status().isOk());
  }
}