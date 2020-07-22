package com.decerto.demo.service.impl.numbers;

import com.decerto.demo.model.DataSource;
import com.decerto.demo.service.DataProvider;
import com.decerto.demo.service.ValueExtractor;
import com.decerto.demo.service.util.RandomNumbersGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class NumberProvider implements DataProvider<BigDecimal> {

    private final ValueExtractor<BigDecimal> valueExtractor;

    private final Map<DataSource, Supplier<BigDecimal>> dataSuppilers = new EnumMap<>(DataSource.class);

    @PostConstruct
    private void getSuppilers(){
        dataSuppilers.put(DataSource.RANDOM_GENERATOR, RandomNumbersGenerator::generateBigDecimal);
        dataSuppilers.put(DataSource.CSV_FILE, valueExtractor::getValue);
    }

    @Override
    public BigDecimal provide(DataSource source) {
        if (dataSuppilers.containsKey(source)) {
            return dataSuppilers.get(source).get();
        }
        throw new IllegalArgumentException();
    }
}
