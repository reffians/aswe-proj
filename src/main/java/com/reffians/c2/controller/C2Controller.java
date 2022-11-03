package com.reffians.c2.controller;

import com.reffians.c2.model.Beacon;
import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.model.User;
import com.reffians.c2.service.C2Service;
import com.reffians.c2.service.CommandService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** The REST Controller of the C2 Application, performs routing of all REST API
 * calls to this service. **/
@RestController
public class C2Controller {
  @Autowired
  private C2Service c2Service;
  @Autowired
  private CommandService commandService;
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
      return responseBadRequest("Invalid beaconid: supplied beaconid is negative.");
    }
    if (status.isPresent() && !Status.isValid(status.get())) {
      logger.warn("GET commands from beacon with invalid status: {}", status);
      return responseBadRequest("Invalid status.");
    }
    List<Command> commands;
    if (status.isPresent()) {
      commands = commandService.getCommands(beaconid, Status.valueOf(status.get()));
    } else {
      commands = commandService.getCommands(beaconid);
    }
    commandService.updateCommandStatus(commands, Status.sent);
    return responseOk(commands);
  }

  /**
   * POST mapping for the register beacon endpoint.
   */
  @PostMapping(path = "/beacon/register", consumes = MediaType.APPLICATION_JSON_VALUE, 
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> registerBeacon(@RequestBody Beacon beacon) {
    String username = beacon.username;
    logger.info("POST register beacon for user with username: {}",
        username);
    List<User> thisUser = c2Service.getUsers(username);
    if (thisUser.size() == 0) {
      logger.error("POST register beacon for non-existent user: {}", username);
      return responseBadRequest("Invalid username: the user does not exist.");
    }
    c2Service.registerBeacon(username);
    Date date = new Date();
    Timestamp t = new Timestamp(date.getTime());
    logger.info("Beacon registered at " + t + " for user: {}",
        username);

    JSONObject obj = new JSONObject();
    obj.put("timestamp", t);
    obj.put("status", 200);
    obj.put("path", "/beacon/register");
    obj.put("username", username);
    obj.put("beacon_id", "beacon_id");
    String bodyToReturn = obj.toString();
    return responseOk(bodyToReturn);
    
  }

  private static <T> ResponseEntity<?> responseOk(@Nullable T body) {
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  private static <T> ResponseEntity<?> responseBadRequest(@Nullable T body) {
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  private static <T> ResponseEntity<?> responseCreated(@Nullable T body) {
    return new ResponseEntity<>(body, HttpStatus.CREATED);
  }
  
  /** 
   * POST mapping to register a new user.

   * @param username is a non null non empty string
   * @param password is a non null non emtpry plaintext password
   */
  @PostMapping(path = "/register")
  public ResponseEntity<?> 
      registerUser(@RequestParam String username, @RequestParam String password) {
    if (username == null || password == null) {
      logger.warn("Registration request missing username or password field"); 
      return responseBadRequest("Registration request missing username or password field");
    } 

    if (username == "" || password == "") {
      logger.warn("Attempted user creation with empty username or password");
      return responseBadRequest("Attempted user creation with empty username or password"); 
    }

    if (!c2Service.userExists(username)) {
      c2Service.insertUser(username, password);
      logger.info("New user created: {}", username);
      return responseCreated("User created");
    }

    logger.warn("Attempted registration for existing user with username: {}", username);
    return responseBadRequest("Attempted registration for existing user"); //
  }   

  /** 
   * POST mapping for login. 

   * @param username is a non null non empty string

   * @param password is a non null non emtpry plaintext password
   */
  @PostMapping(path = "/login")
  public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
    if (username == null || password == null) {
      logger.warn("Login request missing username or password field");
      return responseBadRequest("");
    }

    if (username == "" || password == "") {
      logger.warn("Attempted user creation with empty username or password");
      return responseBadRequest("Attempted user creation with empty username or password"); 
    }

    if (c2Service.compareHash(username, password)) {
      logger.info("User login for {}", username);
      return responseOk("logged in");
    } 

    logger.warn("Incorrect login information attempt for user: {}", username);
    return responseBadRequest("Incorrect login");
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
      return responseBadRequest("Invalid beaconid: supplied beaconid is negative.");
    }

    if (commandContents.isEmpty())  {
      logger.info("POST commands to beacon with empty command contents list.");
      return responseBadRequest("Invalid: empty command contents list.");
    }

    for (String content : commandContents) {
      commandService.addCommand(beaconid, content);
    }
    return responseCreated("added commands"); 
  }
}
