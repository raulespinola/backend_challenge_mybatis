package com.propify.challenge.entity;

import com.propify.challenge.model.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    private int id; // must be null for INSERT and not null for UPDATE

    private String createTime;

    private PropertyType type;

    private double rentPrice; // must be greater than 0, 2 decimal places

    private Address address; // must not be null

    private String emailAddress; // must be a valid email address

    private String code; // not null, only uppercase letters or numbers, 10 characters

}
