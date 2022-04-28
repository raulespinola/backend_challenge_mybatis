package com.propify.challenge.controller;

import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.service.PropertyService;
import com.propify.challenge.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController("/properties")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping("/search/{minRent}/{maxRent}")
    public ResponseEntity<Collection<Property>> search(@NotNull @NotEmpty @RequestAttribute(name = "minRent") String minRentPrice,
                                                      @NotNull @NotEmpty @RequestAttribute(name = "maxRent") String maxRentPrice) {
        return ResponseEntity.ok(propertyService.search(minRentPrice, maxRentPrice));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Property> findById(@NotNull @PathVariable("id") int id) {
        return ResponseEntity.ok(propertyService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void insert(@Valid @RequestBody Property property) {
        propertyService.insert(property);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody Property property) {
        propertyService.update(property);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@NotNull int id) {
        propertyService.delete(id);
    }

    @GetMapping("/report")
    public ResponseEntity<PropertyReport> report() {
        return ResponseEntity.ok(propertyService.propertyReport());
    }
}
