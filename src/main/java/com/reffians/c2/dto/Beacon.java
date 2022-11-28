package com.reffians.c2.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Generated;
import org.springframework.validation.annotation.Validated;


/** Receive Command Request DTO. */
@Generated
@Data
@Validated
public class Beacon {
  @NotNull
  private Integer id;
  @NotEmpty
  private String token;
}
