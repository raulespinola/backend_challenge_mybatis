package com.propify.challenge.entity;

import com.propify.challenge.validation.TimeZoneValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {


    @NotNull
    @NotBlank(message = "Street is mandatory")
    private String street; // must not be null or blank

    @NotNull
    @NotBlank(message = "City is mandatory")
    private String city; // must not be null or blank

    @NotNull
    @NotBlank(message = "State is mandatory")
    @Size(min=2, max=2)
    @Size(min=5, max=5, message="Zip must 2 character code")
    private String state; // 2-letter code, must not be null or blank

    @NotNull
    @NotBlank(message = "zip is mandatory")
    @Size(min=5, max=5, message="Zip must be a 5 digit code")
    private String zip; // 5-digit code, must not be null or blank

    @TimeZoneValidator()
    private String timezone; // Must be a valid timezone

}
