package com.example.demo;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Component
public class BetValidator implements ConstraintValidator<CorrectBetRange, BigDecimal> {

    private final GameProperties gameProperties;

    public BetValidator(GameProperties gameProperties) {
        this.gameProperties = gameProperties;
    }

    @Override
    public boolean isValid(BigDecimal bet, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();

        if (!nonNull(bet)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Value can't be null").addConstraintViolation();
            return false;
        } else if (bet.compareTo(gameProperties.getMinBet()) < 0) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Value too small").addConstraintViolation();
            return false;
        } else if (bet.compareTo(gameProperties.getMaxBet()) >= 1) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Value too big").addConstraintViolation();
            return false;
        }
        return true;

//        return nonNull(bet) && bet.compareTo(new BigDecimal(gameProperties.getMaxBet())) < 1
//                && bet.compareTo(new BigDecimal(gameProperties.getMinBet())) >= 0;
    }
}
