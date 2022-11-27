package com.reffians.c2.filter;

import com.reffians.c2.exception.MalformedAuthorizationHeaderException;
import com.reffians.c2.exception.UserMissingException;
import com.reffians.c2.model.User;
import com.reffians.c2.service.JwtService;
import com.reffians.c2.service.UserService;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/** JWT Filter Class. */
@Component
public class JwtFilter extends OncePerRequestFilter {
  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserService userService;

  private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      String jwt = getJwt(request);
      String username = jwtService.parseJwtSubject(jwt);
      setAuthenticationContext(request, userService.getUser(username));
    } catch (MalformedAuthorizationHeaderException | JwtException | UserMissingException e) {
      logger.error(e.toString());
    }
    filterChain.doFilter(request, response);
  }

  private void setAuthenticationContext(HttpServletRequest request, User user) {
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, 
        null, null);
    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(token);
  }

  private String getJwt(HttpServletRequest request) throws MalformedAuthorizationHeaderException {
    String header = request.getHeader("Authorization");
    if (header == null || header.isEmpty() || !header.startsWith("Bearer")
        || header.split(" ").length != 2) {
      throw new MalformedAuthorizationHeaderException();
    }
    return header.split(" ")[1].trim();
  }
}
