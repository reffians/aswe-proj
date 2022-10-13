package com.reffians.c2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** C2 Application. */
@SpringBootApplication
@RestController
public class C2Application {

  public static void main(String[] args) {
    SpringApplication.run(C2Application.class, args);
  }

  /** Hello world get mapping. */
  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
  }
}
