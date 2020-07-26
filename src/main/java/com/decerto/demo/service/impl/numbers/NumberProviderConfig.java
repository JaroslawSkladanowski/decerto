package com.decerto.demo.service.impl.numbers;

import com.decerto.demo.model.DataSource;
import com.decerto.demo.service.CsvFileReader;
import com.decerto.demo.service.ValueExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

@Configuration
public class NumberProviderConfig {


    @Bean
    public Map<DataSource, ValueExtractor<BigDecimal>> valueExtractorMap(CsvFileReader csvFileReader,
                                                                         RandomNumbersGenerator randomNumbersGenerator){
        Map<DataSource, ValueExtractor<BigDecimal>>  beanMap = new EnumMap<>(DataSource.class);

        beanMap.put(DataSource.RANDOM_GENERATOR, randomNumbersGenerator);
        beanMap.put(DataSource.CSV_FILE, new NumberExtractor(csvFileReader, randomNumbersGenerator));

        return beanMap;
    }
}
