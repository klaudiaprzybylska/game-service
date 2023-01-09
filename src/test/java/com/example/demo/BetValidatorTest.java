package com.example.demo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BetValidatorTest {

    private static final BigDecimal minBet = new BigDecimal(1);

    private static final BigDecimal maxBet = new BigDecimal(10);

    private final GameProperties gameProperties = new GameProperties(minBet, maxBet, new HashMap<>());

    private final BetValidator betValidator = new BetValidator(gameProperties);

    static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(maxBet, true),
                Arguments.of(minBet, true),
                Arguments.of(minBet.subtract(new BigDecimal(1)), false),
                Arguments.of(maxBet.add(new BigDecimal(1)), false),
                Arguments.of(null, false)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void isValidEdgeCases(BigDecimal bet, Boolean expected) {
        // given
        ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);

        // when
        Boolean actual = betValidator.isValid(bet, constraintValidatorContext);

        // then
        assertEquals(expected, actual);
    }
}