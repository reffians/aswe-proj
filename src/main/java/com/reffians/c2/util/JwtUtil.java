package com.reffians.c2.util;

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
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


/** JWT Service. */
@Service
public class JwtUtil {
  private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;
  private static final String ISSUER = "C2.reffians.com";
  private static final SecretKey SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  /** Parse a JWT and return its subject.
   *
   * @param jwt a serialized JWT.
   * @return a subject string of the JWT.
  */
  public static Claims parseJwt(String jwt) throws JwtException {
    return Jwts.parserBuilder()
      .setSigningKey(SECRET)
      .requireIssuer(ISSUER)
      .build()
      .parseClaimsJws(jwt)
      .getBody();
  }

  /** Issue a JWT.
   *
   * @param user a user that is to be issued a JWT.
   * @return a signed, serialized JWT.
  */
  public static String issueJwt(User user) {
    return Jwts.builder()
      .setSubject(user.getUsername())
      .setIssuer(ISSUER)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SECRET, SignatureAlgorithm.HS256)
      .compact();
  }

  public static String getJwtFromRequest(HttpServletRequest request) throws
      MalformedAuthorizationHeaderException {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    return getJwtFromHeader(header);
  }

  public static String getJwtFromHeader(String header) throws
      MalformedAuthorizationHeaderException {
    if (header == null || header.isEmpty() || !header.startsWith("Bearer")
        || header.split(" ").length != 2) {
      throw new MalformedAuthorizationHeaderException();
    }
    return header.split(" ")[1].trim();
  }

  public static String getUsernameFromValidatedJwt(String jwt) throws
      MalformedAuthorizationHeaderException, JwtException {
    return parseJwt(jwt).getSubject();
  }
}
