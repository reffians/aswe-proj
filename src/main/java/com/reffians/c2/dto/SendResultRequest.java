package com.reffians.c2.dto;

import com.reffians.c2.model.Result;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.Generated;
import org.springframework.validation.annotation.Validated;

/** Receive Command Request DTO. */
@Generated
@Data
@Validated
public class SendResultRequest {
  @NotNull
  @Valid
  private Beacon beacon;
  @NotEmpty
  private List<Result> results;
}