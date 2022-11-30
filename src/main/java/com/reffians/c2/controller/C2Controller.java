package com.reffians.c2.controller;

import com.reffians.c2.dto.ReceiveCommandRequest;
import com.reffians.c2.dto.CommandRequest;
import com.reffians.c2.dto.UserRequest;
import com.reffians.c2.exception.CommandContentMismatchException;
import com.reffians.c2.exception.UserExistsException;
import com.reffians.c2.model.Beacon;
import com.reffians.c2.model.User;
import com.reffians.c2.model.commands.Command;
import com.reffians.c2.model.commands.Command.Status;
import com.reffians.c2.service.BeaconService;
import com.reffians.c2.service.CommandService;
import com.reffians.c2.service.UserService;
import com.reffians.c2.util.JwtUtil;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

  /** POST receive commands for a beacon. Returns 200 OK and an array of Command objects on success,
    * 400 Bad Request with an error message on failure.
    *
    * @param request A request object consisting of beacon id, beacon token, and command status that
    *     can be one of "pending", "sent", "executed", "finished", or "all".
    * @return A list of command objects. A command object contains integer "id", integer "beaconid"
    *     of the corresponding beacon, user-defined string "content", and string "status".
    */
  @PostMapping(path = "/beacon/command", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> receiveCommand(@NotEmpty @Valid @RequestBody ReceiveCommandRequest
      request) {
    logger.info("POST beacon receive commands with beaconid: {}", request.getBeacon().getId());
    if (!beaconService.beaconExists(request.getBeacon().getId(), request.getBeacon().getToken())) {
      logger.warn("POST beacon receive commands: no beacon with this beaconid and/or token.");
      return ResponseEntity.badRequest().body("No beacon with this beaconid and/or token.");
    }

    try {
      Integer id = request.getBeacon().getId();
      List<Command> commands = request.statusAll() ? commandService.getCommands(id)
          : commandService.getCommands(id, request.getStatus());
      commandService.updateCommandStatus(commands, Status.sent);
      return ResponseEntity.ok(commands);
    } catch (Exception e) {
      logger.error("POST beacon receive commands unexpected error", e);
      return ResponseEntity.internalServerError().build();
    }
  }

  /**
   * POST mapping for the register beacon endpoint.
   */
  @PostMapping("/beacon/register")
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
  public ResponseEntity<?> registerUser(@NotEmpty @Valid @RequestBody UserRequest userRequest) {
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
  public ResponseEntity<?> login(@NotEmpty @Valid @RequestBody UserRequest userRequest) {
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
  public ResponseEntity<?> submitCommands(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String
      authorizationHeader, @NotEmpty @Valid @RequestBody List<CommandRequest>
      commandRequests) {
    logger.info("POST commands with commandRequests: {}", commandRequests);
    String username = "";
    try {
      String jwt = JwtUtil.getJwtFromHeader(authorizationHeader);
      username = JwtUtil.getUsernameFromValidatedJwt(jwt);
    } catch (Exception e) {
      logger.error("POST submit command by user: incorrect authentication", e);
      return ResponseEntity.internalServerError().build();
    }
    try {
      ArrayList<Command> addedCommands = new ArrayList<>();
      for (CommandRequest commReq : commandRequests) { // need to be able to check that the beacon ids correspond to the right user
        int beaconid = commReq.getBeaconid();
        if (!username.equals(beaconService.getUserForBeacon(beaconid))){
          logger.error("POST submit command by user: user is not authorized for this beacon");
          continue;
        }
        Command c = commandService.addCommand(beaconid, commReq.getCommandType(), commReq.getContent());
        addedCommands.add(c);
      }
      return ResponseEntity.ok(addedCommands); 
    } catch (CommandContentMismatchException e) {
      logger.error("POST commands to beacon: ", e.toString());
      return ResponseEntity.badRequest().body(e.toString());
    } catch (Exception e) {
      logger.error("POST commands to beacon unexpected error", e);
      return ResponseEntity.internalServerError().build();
    }
  }
}
