package com.reffians.c2.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class C2Controller {
  private static final Logger logger = LoggerFactory.getLogger(C2Controller.class);

  @GetMapping("/beacon/command")
  public ResponseEntity<?> getCommandBeacon(@RequestParam String beaconid, @RequestParam(value  = "all", defaultValue = "false") String all) {
    logger.info("GET new commands from beacon with beaconid: {}, all: {}", beaconid, all);
    return new ResponseEntity<>(Arrays.asList(beaconid, all), HttpStatus.OK);
  }

}
