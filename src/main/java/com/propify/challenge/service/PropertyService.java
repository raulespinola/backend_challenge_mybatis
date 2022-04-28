package com.propify.challenge.service;

import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.entity.Property;
import com.propify.challenge.mapper.AddressMapper;
import com.propify.challenge.mapper.PropertyMapper;
import com.propify.challenge.model.PropertyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public interface PropertyService {

    Collection<Property>  search(String minRentPrice, String maxRentPrice);
    Property findById(int id);
    void insert(Property property);
    void update(Property property);
    void delete(int id);
    PropertyReport propertyReport(String minRentPrice, String maxRentPrice);

}
