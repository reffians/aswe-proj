package com.reffians.c2.controller;

import com.reffians.c2.dto.UserRequest;
import com.reffians.c2.exception.UserExistsException;
import com.reffians.c2.model.Beacon;
import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.model.User;
import com.reffians.c2.service.BeaconService;
import com.reffians.c2.service.CommandService;
import com.reffians.c2.service.UserService;
import com.reffians.c2.util.JwtUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** The REST Controller of the C2 Application, performs routing of all REST API
 * calls to this service. **/
@RestController
public class C2Controller {
  @Autowired
  private BeaconService beaconService;
  @Autowired
  private UserService userService;
  @Autowired
  private CommandService commandService;
  @Autowired
  private AuthenticationManager authenticationManager;

  private static final Logger logger = LoggerFactory.getLogger(C2Controller.class);

  /** GET commands for a beacon. Returns 200 OK and an array of command Command
    * objects on success, 400 Bad Request with an error message on failure.
    *
    * @param beaconid A non-negative integer used to identify the beacon.
    * @param status An optional argument specifying the status of the command. Can
    *     be one of "pending", "sent", "executed", or "finished". If no status is
    *     supplied, commands of any status are retrieved.
    * @return A list of command objects. A command object contains integer identifier
    *     "id", integer "beaconid" of the corresponding beacon, user-defined string
    *      "content", and string "status" that is one of "pending", "sent", "executed",
    *      or "finished".
    */
  @GetMapping("/beacon/command")
  public ResponseEntity<?> getCommandBeacon(@RequestParam Integer beaconid,
      @RequestParam Optional<String> status) {
    logger.info("GET commands from beacon with beaconid: {}, status: {}",
        beaconid, status.orElse("NULL"));
    if (beaconid < 0) {
      logger.warn("GET commands from beacon with negative beaconid: {}", beaconid);
      return ResponseEntity.badRequest().body("Invalid beaconid: supplied beaconid is negative.");
    }
    if (status.isPresent() && !Status.isValid(status.get())) {
      logger.error("GET commands from beacon with invalid status: {}", status);
      return ResponseEntity.badRequest().body("Invalid status.");
    }

    try {
      List<Command> commands;
      if (status.isPresent()) {
        commands = commandService.getCommands(beaconid, Status.valueOf(status.get()));
      } else {
        commands = commandService.getCommands(beaconid);
      }
      commandService.updateCommandStatus(commands, Status.sent);
      return ResponseEntity.ok(commands);
    } catch (Exception e) {
      logger.error("GET commands from beacon unexpected error", e);
      return ResponseEntity.internalServerError().build();
    }
  }

  /**
   * GET mapping for the register beacon endpoint.
   */
  @GetMapping(path = "/beacon/register")
  public ResponseEntity<?> registerBeacon(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String
      authorizationHeader) {
    try {
      String jwt = JwtUtil.getJwtFromHeader(authorizationHeader);
      String username = JwtUtil.getUsernameFromValidatedJwt(jwt);
      Beacon beacon = beaconService.registerBeacon(username);
      logger.info("POST register beacon for user with username {}: registered", username);
      return ResponseEntity.ok(beacon);
    } catch (Exception e) {
      logger.error("POST register beacon unexpected error", e);
      return ResponseEntity.internalServerError().build();
    }
  }

  /** 
   * POST mapping to register a new user.

   * @param userRequest is contains two strings:
   *     username is a non-null non-empty string
   *     password is a non-null non-empty plaintext password
   */
  @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
    if (userRequest.getUsername() == null || userRequest.getUsername().isEmpty()) {
      logger.warn("POST register user: Invalid or missing username."); 
      return ResponseEntity.badRequest().body("Invalid or missing username.");
    } 

    if (userRequest.getPassword() == null || userRequest.getPassword().isEmpty()) {
      logger.warn("POST register user: Invalid or missing password."); 
      return ResponseEntity.badRequest().body("Invalid or missing password.");
    } 

    try {
      User user = userService.addUser(userRequest.getUsername(), userRequest.getPassword());
      logger.info("New user created: {}", userRequest.getUsername());
      return ResponseEntity.ok(String.format("User %s created.", user.getUsername()));
    } catch (UserExistsException e) {
      logger.warn("POST register user: {}", e.toString());
      return ResponseEntity.badRequest().body(e.toString()); //
    } catch (Exception e) {
      logger.error("POST register user unexpected error", e);
      return ResponseEntity.internalServerError().build();
    }
  }   

  /** 
   * POST mapping for login. 

   * @param userRequest is contains two strings:
   *     username is a non-null non-empty string
   *     password is a non-null non-empty plaintext password
   */
  @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
    if (userRequest.getUsername() == null || userRequest.getUsername().isEmpty()) {
      logger.error("POST login user: Invalid or missing username."); 
      return ResponseEntity.badRequest().body("Invalid or missing username.");
    } 

    if (userRequest.getPassword() == null || userRequest.getPassword().isEmpty()) {
      logger.error("POST login user: Invalid or missing password."); 
      return ResponseEntity.badRequest().body("Invalid or missing password.");
    } 

    try {
      Authentication authentication = authenticationManager.authenticate(
          userRequest.getAuthenticationToken());
      User user = (User) authentication.getPrincipal();
      logger.info("User {} logged in.", user.getUsername());
      return ResponseEntity.ok().body(JwtUtil.issueJwt(user));
    } catch (BadCredentialsException e) {
      logger.error("POST login user: {}", e.toString());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); //
    } catch (Exception e) {
      logger.error("POST login user unexpected error", e);
      return ResponseEntity.internalServerError().build();
    }
  }

  /**
    * POST User Commands. Returns 300 Created on success, and 400 Bad Request
    * on failure.
    *
    * @param beaconid a non-negative integer representing the beaconid.
    * @param commandContents a list of strings representing the command contents.
    * @return ResponseEntity with HttpStatus
    */
  @PostMapping(path = "/user/command", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> submitCommands(@RequestParam Integer beaconid,
      @RequestBody List<String> commandContents) {
    logger.info("POST commands to beacon with beaconid: {}, commandContents: {}",
        beaconid, commandContents);
    if (beaconid < 0) {
      logger.info("POST commands to beacon with negative beaconid: {}", beaconid);
      return ResponseEntity.badRequest().body("Invalid beaconid: supplied beaconid is negative.");
    }

    if (commandContents.isEmpty())  {
      logger.info("POST commands to beacon with empty command contents list.");
      return ResponseEntity.badRequest().body("Invalid: empty command contents list.");
    }

    try {
      ArrayList<Command> addedCommands = new ArrayList<Command>();
      for (String content : commandContents) {
        addedCommands.add(commandService.addCommand(beaconid, content));
      }
      return ResponseEntity.ok(addedCommands); 
    } catch (Exception e) {
      logger.error("POST commands to beacon unexpected error", e);
      return ResponseEntity.internalServerError().build();
    }
  }
}
