package com.example.demo;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BetValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectBetRange {

    String message() default "Invalid bet amount";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
