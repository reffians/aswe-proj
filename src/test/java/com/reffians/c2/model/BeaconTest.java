package com.reffians.c2.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeaconTest {
  @Test
  void testBeaconConstructorUsernameNonEmpty() {
    String username = "username";
    Beacon beacon = new Beacon(username);
    assertEquals(beacon.getUsername(), username);
  }

  @Test
  void testBeaconConstructorUsernameEmpty() {
    String username = "";
    Beacon beacon = new Beacon(username);
    assertEquals(beacon.getUsername(), username);
  }

  @Test
  void testBeaconConstructorUsernameNull() {
    Beacon beacon = new Beacon(null);
    assertNull(beacon.getUsername());
  }

  @Test
  void testBeaconConstructorTokenValidBase64() {
    Beacon beacon = new Beacon("username");
    assertDoesNotThrow(() -> {
      Base64.decodeBase64URLSafe(beacon.getToken());
    });
  }

  @Test
  void testBeaconConstructorTokenLen64Bytes() {
    Beacon beacon = new Beacon("username");
    byte[] bytes = Base64.decodeBase64URLSafe(beacon.getToken());
    assertEquals(bytes.length, 64);
  }

  @Test
  void testGenerateTokenValidBase64() {
    assertDoesNotThrow(() -> {
      Base64.decodeBase64URLSafe(Beacon.generateToken());
    });
  }

  @Test
  void testGenerateTokenLen64Bytes() {
    assertEquals(Base64.decodeBase64URLSafe(Beacon.generateToken()).length, 64);
  }
}
