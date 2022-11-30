package com.reffians.c2.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.Generated;
import org.springframework.validation.annotation.Validated;

import com.reffians.c2.model.commands.Command.Status;

/** Receive Command Request DTO. */
@Generated
@Data
@Validated
public class ReceiveCommandRequest {
  @NotNull
  @Valid
  private Beacon beacon;
  @NotEmpty
  @Pattern(regexp = "pending|sent|executed|finished|all")
  private String status;

  public boolean statusAll() {
    return status.equals("all");
  }
  
  public Status getStatus() {
    return Status.valueOf(status);
  }
}
