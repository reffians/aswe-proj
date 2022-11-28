package com.reffians.c2.dto;

import lombok.Data;
import lombok.Generated;

/** User Request DTO. */
@Generated
@Data
public class CommandRequest {
  private Integer beaconid;
  private String commandType;
  private String content;
}
