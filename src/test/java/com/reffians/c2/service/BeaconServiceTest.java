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
import com.reffians.c2.model.Beacon;
import com.reffians.c2.repository.BeaconRepository;

@SpringBootTest
class BeaconServiceTest {
  @Autowired
  private BeaconService beaconService;

  @MockBean
  private BeaconRepository beaconRepository;

  @Test
  void testRegisterBeacon() {
    String username = "username";
    Beacon beacon = new Beacon(username);
    Mockito.when(beaconRepository.save(any(Beacon.class))).thenReturn(beacon);
    assertEquals(beacon, beaconService.registerBeacon(username));
  }

  @Test
  void testBeaconExistsExists() {
    Integer beaconid = 1;
    String token = "token";
    Mockito.when(beaconRepository.findByIdAndToken(beaconid, token))
        .thenReturn(List.of(new Beacon("username")));
    assertTrue(beaconService.beaconExists(beaconid, token));
  }

  @Test
  void testBeaconExistsNexists() {
    Integer beaconid = 1;
    String token = "token";
    Mockito.when(beaconRepository.findByIdAndToken(beaconid, token))
        .thenReturn(Collections.emptyList());
    assertFalse(beaconService.beaconExists(beaconid, token));
  }

  @Test
  void testGetUserForBeacon() {
    Integer beaconid = 1;
    String username = "username";
    Mockito.when(beaconRepository.findUserForBeacon(beaconid)).thenReturn(username);
    assertEquals(username, beaconService.getUserForBeacon(beaconid));
  }
}
