package com.gruzini.tennistico.models.forms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;

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
  private String street;
  private String city;
  private String state;
  private String country;
  private String postcode;

  @AssertTrue(message = "Insufficient data. Please choose another court.")
  @JsonIgnore
  public boolean isDataSufficient(){
    return street != null && street.length() != 0
            && city != null && city.length() != 0
            && country != null && country.length() != 0;
  }
}
