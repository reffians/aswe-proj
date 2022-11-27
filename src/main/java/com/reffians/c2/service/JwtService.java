package com.reffians.c2.service;

import com.reffians.c2.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/** JWT Service. */
@Service
public class JwtService {
  private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;
  private static final String ISSUER = "C2.reffians.com";
  private static final SecretKey SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  /** Parse a JWT and return its subject.
   *
   * @param jwt a serialized JWT.
   * @return a subject string of the JWT.
  */
  public String parseJwtSubject(String jwt) throws ExpiredJwtException, UnsupportedJwtException,
      MalformedJwtException, SecurityException, IllegalArgumentException {
    return Jwts.parserBuilder()
      .setSigningKey(SECRET)
      .requireIssuer(ISSUER)
      .build()
      .parseClaimsJws(jwt)
      .getBody()
      .getSubject();
  }

  /** Issue a JWT.
   *
   * @param user a user that is to be issued a JWT.
   * @return a signed, serialized JWT.
  */
  public String issueJwt(User user) {
    return Jwts.builder()
      .setSubject(user.getUsername())
      .setIssuer(ISSUER)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SECRET, SignatureAlgorithm.HS256)
      .compact();
  }
}
