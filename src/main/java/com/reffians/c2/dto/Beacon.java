package com.reffians.c2.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Generated;

/** Receive Command Request DTO. */
@Generated
@Data
@Valid
public class Beacon {
  @NotNull
  private Integer id;
  @NotEmpty
  private String token;
}
