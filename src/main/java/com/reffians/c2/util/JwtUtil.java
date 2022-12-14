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
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;


/** JWT Util Class. */
@NoArgsConstructor(access = AccessLevel.PRIVATE) 
public class JwtUtil {
  private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000L;
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

  /** Extracts a JWT string from an HTTP request.
   *
   * @param request an HTTP request object.
   * @return a JWT string.
   * @throws MalformedAuthorizationHeaderException if JWT extraction fails.
   */
  public static String getJwtFromRequest(HttpServletRequest request) throws
      MalformedAuthorizationHeaderException {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    return getJwtFromHeader(header);
  }

  /** Extracts a JWT string from an HTTP header.
   *
   * @param header an HTTP request header, typically the Authorization header.
   * @return a JWT string.
   * @throws MalformedAuthorizationHeaderException if JWT extraction fails.
   */
  public static String getJwtFromHeader(String header) throws
      MalformedAuthorizationHeaderException {
    if (header == null || header.isEmpty() || !header.startsWith("Bearer")
        || header.split(" ").length != 2) {
      throw new MalformedAuthorizationHeaderException();
    }
    return header.split(" ")[1].trim();
  }

  /** Extracts the username from a validated JWT. 
   *
   * @param jwt a JWT.
   * @return the username associated with the JWT.
   * @throws JwtException if JWT parsing/validation fails.
   */
  public static String getUsernameFromValidatedJwt(String jwt) throws JwtException {
    return parseJwt(jwt).getSubject();
  }
}
