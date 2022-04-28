package com.propify.challenge.entity;

import com.propify.challenge.model.PropertyType;
import com.propify.challenge.validation.EmailExtendValidator;
import com.propify.challenge.validation.PositiveDecimalValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id; // must be null for INSERT and not null for UPDATE

    private String createTime;

    private PropertyType propertyType;

    @PositiveDecimalValidator()
    private double rentPrice; // must be greater than 0, 2 decimal places

    @NotNull
    private Address address; // must not be null

    @EmailExtendValidator
    private String emailAddress; // must be a valid email address

    @NotNull
    @Size(min= 10, max= 10, message= "Must be 10 chars code")
    @Pattern(regexp ="^[A-Z0-9]+$", message = "Error")
    private String code; // not null, only uppercase letters or numbers, 10 characters

}
