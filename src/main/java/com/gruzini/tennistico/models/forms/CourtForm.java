package com.gruzini.tennistico.models.forms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourtForm {
  private String name;
  private String email;
  private String phoneNumber;
  private String website;

  private String houseNumber;
  @NotNull
  @NotEmpty
  private String street;
  @NotNull
  @NotEmpty
  private String city;
  private String state;
  @NotNull
  @NotEmpty
  private String country;
  private String postcode;
}
