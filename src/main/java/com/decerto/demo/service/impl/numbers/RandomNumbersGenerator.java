package com.decerto.demo.service.impl.numbers;

import com.decerto.demo.service.ValueExtractor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class RandomNumbersGenerator implements ValueExtractor<BigDecimal> {

    @Override
    public BigDecimal getValue() {
        return BigDecimal.valueOf(new Random().nextDouble());
    }

    public int generateInt(int bound) {
        return new Random().nextInt(bound);
    }
}
