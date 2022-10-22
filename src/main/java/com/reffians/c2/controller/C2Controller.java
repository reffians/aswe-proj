package com.reffians.c2.controller;

import com.reffians.c2.model.Command;
import com.reffians.c2.model.Command.Status;
import com.reffians.c2.service.C2Service;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}
