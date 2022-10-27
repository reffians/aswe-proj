package com.reffians.c2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/** The class representing the entire C2 application. */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class C2Application {
  public static void main(String[] args) {
    SpringApplication.run(C2Application.class, args);
  }
}
