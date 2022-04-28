package com.propify.challenge;

import com.propify.challenge.controller.PropertyController;
import com.propify.challenge.entity.Address;
import com.propify.challenge.entity.Property;
import com.propify.challenge.model.PropertyType;
import com.propify.challenge.service.PropertyService;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.any;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertyController.class)
class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyService propertyService;

    @Before
    private void populateValues() {

    }

    @Test
    public void testSearch() throws Exception {

        String url = "/properties/search/0/100";

        Property propertyTest = Property.builder()
                .address(Address.builder()
                        .street("Timbo132")
                        .city("Cordoba")
                        .state("CA")
                        .zip("12345")
                        .timezone("GMT")
                        .build())
                .code("AR")
                .createTime((new Date()).toString())
                .emailAddress("raulespi@gmail.com")
                .rentPrice(100.00)
                .propertyType(PropertyType.CONDOMINIUM)
                .build();
        Collection<Property> propertyCollection= new ArrayList<>();
        propertyCollection.add(propertyTest);

        when(propertyService.search(any().toString(),any().toString()))
                .thenReturn(propertyCollection);
        given()
                .header("Content-type", "application/json")
                .post(url)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("property.code", equalTo("AR"));


        // TODO: add assertions
    }

    // TODO: add tests for other API methods
}
