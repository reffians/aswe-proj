package com.reffians.c2.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.reffians.c2.exception.MalformedAuthorizationHeaderException;
import com.reffians.c2.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;


@SpringBootTest
class JwtUtilTest {
  private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000L;
  private static final String ISSUER = (String) ReflectionTestUtils
      .getField(JwtUtil.class, "ISSUER");
  private static final SecretKey SECRET = (SecretKey) ReflectionTestUtils
      .getField(JwtUtil.class, "SECRET");
  private static final String username = "username";
  private static User user;

  @BeforeAll
  static void newUser() {
    user = new User(username, "password", new BCryptPasswordEncoder());
  }

  private static String issueJwt(String username, String issuer, Long expirationTime,
      SecretKey secret) {
    return Jwts.builder()
      .setSubject(username)
      .setIssuer(issuer)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
      .signWith(secret, SignatureAlgorithm.HS256)
      .compact();
  }

  @Test
  void testParseJwt() {
    String jwt = issueJwt(username, ISSUER, EXPIRATION_TIME, SECRET);
    assertEquals(username, JwtUtil.parseJwt(jwt).getSubject());
  }

  @Test
  void testParseJwtWrongKey() {
    SecretKey wrongKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    String jwt = issueJwt(username, ISSUER, EXPIRATION_TIME, wrongKey);
    assertThrows(JwtException.class, () -> {JwtUtil.parseJwt(jwt);});
  }

  @Test
  void testParseJwtWrongIssuer() {
    String jwt = issueJwt(username, "wrong.issuer.com", EXPIRATION_TIME, SECRET);
    assertThrows(JwtException.class, () -> {JwtUtil.parseJwt(jwt);});
  }

  @Test
  void testParseJwtExpired() {
    String jwt = issueJwt(username, ISSUER, 1L, SECRET);
    assertThrows(JwtException.class, () -> {JwtUtil.parseJwt(jwt);});
  }

  private static Claims parseJwt(String jwt, SecretKey secret, String issuer) throws JwtException {
    return Jwts.parserBuilder()
      .setSigningKey(secret)
      .requireIssuer(issuer)
      .build()
      .parseClaimsJws(jwt)
      .getBody();
  }

  @Test
  void testIssueJwtUsername() {
    String jwt = JwtUtil.issueJwt(user);
    assertEquals(user.getUsername(), parseJwt(jwt, SECRET, ISSUER).getSubject());
  }

  @Test
  void testIssueJwtIssuer() {
    String jwt = JwtUtil.issueJwt(user);
    assertEquals(ISSUER, parseJwt(jwt, SECRET, ISSUER).getIssuer());
  }

  @Test
  void testIssueJwtDateShortlyEarlier() {
    String jwt = JwtUtil.issueJwt(user);
    Date fiveSecondsBefore = new Date(System.currentTimeMillis() - 5 * 1000L);
    assertTrue(parseJwt(jwt, SECRET, ISSUER).getIssuedAt().compareTo(fiveSecondsBefore) > 0);
  }

  @Test
  void testIssueJwtDateShortlyLater() {
    String jwt = JwtUtil.issueJwt(user);
    Date fiveSecondsAfter = new Date(System.currentTimeMillis() + 5 * 1000L);
    assertTrue(parseJwt(jwt, SECRET, ISSUER).getIssuedAt().compareTo(fiveSecondsAfter) < 0);
  }

  @Test
  void testIssueJwtExpirationShortlyEarlier() {
    String jwt = JwtUtil.issueJwt(user);
    Date fiveSecondsBefore = new Date(System.currentTimeMillis() + EXPIRATION_TIME - 5 * 1000L);
    assertTrue(parseJwt(jwt, SECRET, ISSUER).getExpiration().compareTo(fiveSecondsBefore) > 0);
  }

  @Test
  void testIssueJwtExpirationShortlyLater() {
    String jwt = JwtUtil.issueJwt(user);
    Date fiveSecondsAfter = new Date(System.currentTimeMillis() + EXPIRATION_TIME + 5 * 1000L);
    assertTrue(parseJwt(jwt, SECRET, ISSUER).getExpiration().compareTo(fiveSecondsAfter) < 0);
  }

  @Test
  void testIssueJwtWrongSecret() {
    String jwt = JwtUtil.issueJwt(user);
    SecretKey wrongKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    assertThrows(JwtException.class, () -> {parseJwt(jwt, wrongKey, ISSUER);});
  }

  @Test
  void testGetJwtFromRequest() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    String jwt = issueJwt(username, ISSUER, EXPIRATION_TIME, SECRET);
    String header = "Bearer " + issueJwt(username, ISSUER, EXPIRATION_TIME, SECRET);
    Mockito.when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(header);
    assertDoesNotThrow(() -> {assertEquals(jwt, JwtUtil.getJwtFromRequest(request));});
  }

  @Test
  void testGetJwtFromHeaderHeaderNull() {
    assertThrows(MalformedAuthorizationHeaderException.class, () -> {
      JwtUtil.getJwtFromHeader(null);
    });
  }

  @Test
  void testGetJwtFromHeaderHeaderEmpty() {
    assertThrows(MalformedAuthorizationHeaderException.class, () -> {
      JwtUtil.getJwtFromHeader("");
    });
  }

  @Test
  void testGetJwtFromHeaderHeaderNoBearerPrefix() {
    assertThrows(MalformedAuthorizationHeaderException.class, () -> {
      JwtUtil.getJwtFromHeader("notempty");
    });
  }

  @Test
  void testGetJwtFromHeaderHeaderBearerPrefixTooShort() {
    assertThrows(MalformedAuthorizationHeaderException.class, () -> {
      JwtUtil.getJwtFromHeader("Bearer");
    });
  }

  @Test
  void testGetJwtFromHeaderHeaderBearerPrefixTooLong() {
    assertThrows(MalformedAuthorizationHeaderException.class, () -> {
      JwtUtil.getJwtFromHeader("Bearer something more");
    });
  }

  @Test
  void testGetJwtFromHeaderHeader() {
    String token = "token";
    assertDoesNotThrow(() -> {assertEquals(token, JwtUtil.getJwtFromHeader("Bearer " + token));});
  }

  @Test
  void testGetUsernameFromValidatedJwt() {
    String jwt = issueJwt(username, ISSUER, EXPIRATION_TIME, SECRET);
    assertEquals(JwtUtil.getUsernameFromValidatedJwt(jwt), username);
  }

  @Test
  void testGetUsernameFromValidatedJwtWrongKey() {
    SecretKey wrongKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    String jwt = issueJwt(username, ISSUER, EXPIRATION_TIME, wrongKey);
    assertThrows(JwtException.class, () -> {JwtUtil.getUsernameFromValidatedJwt(jwt);});
  }

  @Test
  void testGetUsernameFromValidatedJwtWrongIssuer() {
    String jwt = issueJwt(username, "wrong.issuer.com", EXPIRATION_TIME, SECRET);
    assertThrows(JwtException.class, () -> {JwtUtil.getUsernameFromValidatedJwt(jwt);});
  }

  @Test
  void testGetUsernameFromValidatedJwtExpired() {
    String jwt = issueJwt(username, ISSUER, 1L, SECRET);
    assertThrows(JwtException.class, () -> {JwtUtil.getUsernameFromValidatedJwt(jwt);});
  }
}
