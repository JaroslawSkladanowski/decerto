package com.decerto.demo.services.utils;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Random;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BigDecimalGenerator {

    private BigDecimalGenerator() {
        throw new AssertionError();
    }

    static Random random = new Random(System.currentTimeMillis());

    public static BigDecimal generate() {
        return BigDecimal.valueOf(random.nextInt() * random.nextDouble() * (random.nextBoolean() ? 1 : -1));
    }
}
