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
        return nonNull(bet) && bet.compareTo(new BigDecimal(gameProperties.getMaxBet())) < 1
                && bet.compareTo(new BigDecimal(gameProperties.getMinBet())) >= 0;
    }
}
