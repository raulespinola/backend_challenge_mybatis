package com.propify.challenge.validation;


import javax.validation.Constraint;
import javax.validation.constraints.Positive;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Positive()
@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = PositiveDecimalConstraint.class)
@Documented
public @interface PositiveDecimalValidator {
}