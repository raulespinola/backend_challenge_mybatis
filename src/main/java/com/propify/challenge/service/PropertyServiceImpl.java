package com.propify.challenge.service;

import com.propify.challenge.entity.Property;
import com.propify.challenge.mapper.AddressMapper;
import com.propify.challenge.mapper.PropertyMapper;
import com.propify.challenge.model.PropertyReport;
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


@Component
public class PropertyServiceImpl implements PropertyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyServiceImpl.class);

    @Autowired
    PropertyMapper propertyMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    AlertServiceImpl alertServiceImpl;


    public Collection<Property> search(String minRentPrice, String maxRentPrice) {
        return propertyMapper.search(minRentPrice, maxRentPrice);
    }

    public Property findById(int id) {
        return propertyMapper.findById(id);
    }

    public void insert(Property property) {
        propertyMapper.insert(property);
        LOGGER.info("CREATED: {}", property.getId());
    }

    public void update(Property property) {
        propertyMapper.update(property);
        LOGGER.info("UPDATED: {}", property.getId());
    }

    public void delete(int id) {

        // TODO: Sending the alert should be non-blocking (asynchronous)
        //  Extra points for only sending the alert when/if the transaction is committed
        Runnable runnable = () -> {
            propertyMapper.delete(id);
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<Void> completableFutureDelete =CompletableFuture.runAsync(runnable, executor);
        completableFutureDelete.thenRun(() -> {
            alertServiceImpl.sendPropertyDeletedAlert(id);
            LOGGER.info("DELETED: {}", id);
        });
        executor.shutdown();


    }

    public PropertyReport propertyReport() {
        var allProperties = propertyMapper.search(null, null);
        var propertyReport = new PropertyReport();

        // Calculate total quantity
        propertyReport.setTotalQuantity(allProperties.size());

        // Calculate the quantity of each type, 0 if there is no properties.
        Map<PropertyType, Long> quantityPerType  = allProperties
                .stream()
                .collect(Collectors.groupingBy(
                        Property::getPropertyType,
                        Collectors.mapping(Property::getPropertyType, Collectors.counting())));

        propertyReport.setQuantityPerType(quantityPerType);

        // Calculate the average rent price (exclude the properties without rent price or with rent price = 0)
        propertyReport.setAverageRentPrice(allProperties.stream()
                .collect(Collectors.averagingDouble(a->a.getRentPrice())));
        // Calculate the quantity of properties in the state of Illinois (IL)
        propertyReport.setIllinoisQuantity((int)allProperties.stream()
                .filter(a->a.getAddress().getState().equals("IL")).count());

        return propertyReport;
    }
}
