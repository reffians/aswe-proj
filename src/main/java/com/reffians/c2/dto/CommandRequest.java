package com.reffians.c2.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.Generated;
import org.springframework.validation.annotation.Validated;


/** User Request DTO. */
@Generated
@Data
@Validated
public class CommandRequest {
  @NotNull
  private Integer beaconid;
  @Pattern(regexp = "STOP|SLEEP|UPLOAD|DOWNLOAD|GETHOSTNAME|GETHOSTOS")
  private String commandType;
  @NotNull
  private String content;
}
