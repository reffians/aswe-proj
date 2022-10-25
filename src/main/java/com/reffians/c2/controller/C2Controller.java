package com.reffians.c2.controller;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.CommandList;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.service.C2Service;
import com.reffians.c2.model.User;
import java.util.Optional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** C2 Controller Class. **/
@RestController
public class C2Controller {
  @Autowired
  private C2Service c2Service;
  

  private static final Logger logger = LoggerFactory.getLogger(C2Controller.class);

  /** GET Command Beacon. **/
  @GetMapping("/beacon/command")
  public ResponseEntity<?> getCommandBeacon(@RequestParam Integer beaconid,
      @RequestParam Optional<String> status) {
    logger.info("GET commands from beacon with beaconid: {}, status: {}",
        beaconid, status.orElse("NULL"));

    if (beaconid < 0) {
      logger.info("GET commands from beacon with negative beaconid: {}", beaconid);
      return responseBadRequest();
    }

    if (!status.isPresent()) {
      return responseOk(c2Service.getCommands(beaconid));
    }

    if (!Command.isValidStatus(status.get())) {
      logger.info("GET commands from beacon with invalid status: {}", status);
      return responseBadRequest();
    }

    return responseOk(c2Service.getCommands(beaconid, Status.valueOf(status.get())));
  }

  private static <T> ResponseEntity<?> responseOk(@Nullable T body) {
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  private static <T> ResponseEntity<?> responseBadRequest() {
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
  
  /* POST Register User. */
  @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    String username = user.username;
    String password = user.password;
    List<User> thisUser = c2Service.getUsers(username);
    if (thisUser.size() == 0) {
      c2Service.addUser(username, password);
      return new ResponseEntity<>("Registered", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("User Already Exists", HttpStatus.OK);
    }
   }   

  /* POST Login User. */
  @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> login(@RequestBody User user) {
    String username = user.username;
    String password = user.password;
    List<User> thisUser = c2Service.getUsers(username, password);
    if (thisUser.size() != 0) {
      return new ResponseEntity<>("logged in", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("user does not exist or password incorrect", HttpStatus.OK);
    }
  }

  /* POST Beacon Commands (one user submits batch commands to one beacon) */
  @PostMapping(path="/beacon/command", consumes = MediaType.APPLICATION_JSON_VALUE) // , @RequestBody User user
  public ResponseEntity<?> submitCommands(@RequestBody CommandList commands) {
    // user is needed to insert into beacons table (col1: beaconid, col2: userid)
  //  Integer id = null;
    Integer beaconid = null;
    String content = "";

    for (Command c: commands.getCommands())
    {
    //  id = c.id;
      beaconid = c.beaconid;
      content = c.content;
      c2Service.addCommand(beaconid, content);
    }
    return new ResponseEntity<>("added commands", HttpStatus.OK); 
   }
}
