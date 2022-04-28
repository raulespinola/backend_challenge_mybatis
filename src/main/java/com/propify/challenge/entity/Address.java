package com.propify.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    private String street; // must not be null or blank

    private String city; // must not be null or blank

    private String state; // 2-letter code, must not be null or blank

    private String zip; // 5-digit code, must not be null or blank

    private String timezone; // Must be a valid timezone

}
