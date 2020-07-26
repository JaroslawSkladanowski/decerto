package com.decerto.demo.service.impl.numbers;

import com.decerto.demo.model.DataSource;
import com.decerto.demo.service.DataProvider;
import com.decerto.demo.service.ValueExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NumberProvider implements DataProvider<BigDecimal> {

    private final Map<DataSource, ValueExtractor<BigDecimal>> valueExtractor;

    @Override
    public BigDecimal provide(DataSource source) {
        if (valueExtractor.containsKey(source)) {
            return valueExtractor.get(source).getValue();
        }
        throw new IllegalArgumentException();
    }
}
