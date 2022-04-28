package com.propify.challenge.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.TimeZone;

public class TimeZoneConstraint implements ConstraintValidator<TimeZoneValidator, String> {

        @Override
        public void initialize(TimeZoneValidator constraintAnnotation) {
        }

        @Override
        public boolean isValid(String timeZone, ConstraintValidatorContext constraintValidatorContext) {
            return Set.of(TimeZone.getAvailableIDs()).contains(timeZone);
        }
}
