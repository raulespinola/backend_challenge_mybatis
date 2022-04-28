package com.propify.challenge;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.propify.challenge.entity.Property;
import com.propify.challenge.mapper.PropertyMapper;
import com.propify.challenge.model.PropertyType;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@MybatisTest
@SpringJUnitConfig
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DatabaseSetup("PropertyMapperTest.xml")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class PropertyMapperTest {

    @Autowired
    PropertyMapper propertyMapper;

    @Test
    public void testInsert() {
        var property = new Property();
        property.setRentPrice(3000.99);
        property.setPropertyType(PropertyType.MULTI_FAMILY);
        propertyMapper.insert(property);

        // TODO: add assertions
    }

    @Test
    public void testFindById() {
        var property = propertyMapper.findById(1);

        assert property != null;
        // TODO: add assertions
    }

    // TODO: Add more tests
}
