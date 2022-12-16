package com.reffians.c2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

import com.reffians.c2.model.Beacon;
import com.reffians.c2.model.User;
import com.reffians.c2.model.Result;
import com.reffians.c2.service.BeaconService;
import com.reffians.c2.service.CommandService;
import com.reffians.c2.service.ResultService;
import com.reffians.c2.service.UserService;
import com.reffians.c2.controller.C2Controller;


@ComponentScan
@Configuration
public class IntegrationTestConfig {
  @Bean
  public C2Controller c2Controller()
  {
    return new C2Controller();
  }

  @Bean
  public BeaconService beaconService()
  {
    return new BeaconService();
  }

  @Bean
  public CommandService commandService()
  {
    return new CommandService();
  }

  @Bean
  public ResultService resultService()
  {
    return new ResultService();
  }

  @Bean
  public UserService userService()
  {
    return new UserService();
  }

  @Bean
  public Beacon beacon()
  {
    return new Beacon();
  }

  @Bean
  public Result result()
  {
    return new Result();
  }

  @Bean
  public User user()
  {
    return new User();
  }
}