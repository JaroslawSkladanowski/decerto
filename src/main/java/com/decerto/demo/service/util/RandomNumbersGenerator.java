package com.decerto.demo.service.util;

import java.math.BigDecimal;
import java.util.Random;

public class RandomNumbersGenerator {

    private RandomNumbersGenerator() {
        throw new AssertionError();
    }

    private static final Random random = new Random();

    public static BigDecimal generateBigDecimal() {
        return BigDecimal.valueOf(random.nextDouble());
    }

    public static int generateInt(int bound) {
        return random.nextInt(bound);
    }
}
