package com.decerto.demo.service.impl.numbers;

import com.decerto.demo.model.BigDecimalExtractorException;
import com.decerto.demo.service.CsvFileReader;
import com.decerto.demo.service.ValueExtractor;
import com.decerto.demo.service.util.RandomNumbersGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NumberExtractor implements ValueExtractor<BigDecimal> {

    public static final String VERIFY_INPUT_FILE_MESSAGE = "Verify the syntax of the input file!";

    private final CsvFileReader csvFileReader;

    @Override
    public BigDecimal getValue() {
        try {
            return getRandomFromCollection(
                    mapAllToBigDecimal(
                            csvFileReader.getValuesFromCsvFile()
                    )
            );
        } catch (NumberFormatException e) {
            throw new BigDecimalExtractorException(VERIFY_INPUT_FILE_MESSAGE);
        } catch (Exception e) {
            throw new BigDecimalExtractorException(e);
        }
    }

    private Collection<BigDecimal> mapAllToBigDecimal(String[] values) {
        return Arrays
                .stream(values)
                .map(BigDecimal::new)
                .collect(Collectors.toList());
    }

    private BigDecimal getRandomFromCollection(Collection<BigDecimal> bigDecimals) {
        return bigDecimals.stream()
                .skip(RandomNumbersGenerator.generateInt(bigDecimals.size()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
