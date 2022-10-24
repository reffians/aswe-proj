package com.reffians.c2.controller;

import java.util.Optional;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.service.C2Service;
import com.reffians.c2.model.User;

@RestController
public class C2Controller {
  @Autowired
  private C2Service c2Service;
  

  private static final Logger logger = LoggerFactory.getLogger(C2Controller.class);

  @GetMapping("/beacon/command")
  public ResponseEntity<?> getCommandBeacon(@RequestParam Integer beaconid, @RequestParam Optional<String> status) {
    logger.info("GET new commands from beacon with beaconid: {}, status: {}", beaconid, status.orElse("NULL"));
    if (!status.isPresent()) {
      return new ResponseEntity<>(c2Service.getCommands(beaconid), HttpStatus.OK);
    }
    if (!Command.isValidStatus(status.get())) {
      logger.info("GET new commands from beacon with invalid status {}", status);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(c2Service.getCommands(beaconid, Status.valueOf(status.get())), HttpStatus.OK);
  }
  //send command batch
  /* 
  @PostMapping(path = "/beacon/command", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getCommandBatchBeacon(@RequestParam Integer beaconid, @RequestParam String commandid, @RequestParam String result)
  {
	//need to do something with the result
	List<Command> thisCommand = c2Service.getCommand(beaconid, commandid);
	if (thisCommand != null && thisCommand.size() != 0) {
		c2Service.updateCommand(beaconid, commandid);
		return new ResponseEntity<>("Registered", HttpStatus.OK);
	}
	else {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
  }*/
  
  //register and login
  @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> registerUser(@RequestBody User user) {
	String username = user.username;
	String password = user.password;
	List<User> thisUser = c2Service.getUsers(username);
	if (thisUser.size() == 0) //kinda jank 
	{
		c2Service.addUser(username, password);
		return new ResponseEntity<>("Registered", HttpStatus.OK);
	}
	else {
		return new ResponseEntity<>("User Already Exists", HttpStatus.OK);
	}
  }

   @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> login(@RequestBody User user) {
	String username = user.username;
	String password = user.password;
	List<User> thisUser = c2Service.getUsers(username, password);
	if (thisUser.size() != 0) //kinda jank 
	{
		return new ResponseEntity<>("logged in", HttpStatus.OK);
  	}
	else{
		return new ResponseEntity<>("user does not exist or password incorrect", HttpStatus.OK);
	}

  }
}
