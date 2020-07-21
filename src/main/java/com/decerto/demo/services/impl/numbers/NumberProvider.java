package com.decerto.demo.services.impl.numbers;

import com.decerto.demo.models.DataSource;
import com.decerto.demo.services.DataProvider;
import com.decerto.demo.services.utils.BigDecimalGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class NumberProvider implements DataProvider<BigDecimal> {

    private final Map<DataSource, Supplier<BigDecimal>> dataSuppilers = new EnumMap<>(DataSource.class);

    @PostConstruct
    private void getSuppilers(){
        dataSuppilers.put(DataSource.RANDOM_GENERATOR, BigDecimalGenerator::generate);
    }

    @Override
    public BigDecimal provide(DataSource sources) {
        return dataSuppilers.containsKey(sources) ? dataSuppilers.get(sources).get() : BigDecimal.ZERO;
    }
}
