package com.propify.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyReport {

    private Integer totalQuantity;

    private Map<PropertyType, Long> quantityPerType;

    private double averageRentPrice;

    private Integer illinoisQuantity;
}
