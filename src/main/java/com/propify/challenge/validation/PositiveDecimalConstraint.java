package com.propify.challenge.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PositiveDecimalConstraint implements ConstraintValidator<PositiveDecimalValidator, Number> {

    @Override
    public void initialize(PositiveDecimalValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        return (BigDecimal.valueOf((Long)number).scale() == 2);
    }

}
