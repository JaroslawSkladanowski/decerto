package com.decerto.demo.controller;

import com.decerto.demo.model.DataSource;
import com.decerto.demo.service.DataProvider;
import com.decerto.demo.service.JoiningService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/numbers")
@RequiredArgsConstructor
@Slf4j
public class NumericController {

    private final JoiningService<BigDecimal> bigDecimalConnectingService;
    private final DataProvider<BigDecimal> bigDecimalDataProvider;

    @GetMapping("/random/{number}")
    public ResponseEntity<BigDecimal> addNumberWithGeneratedValue(@PathVariable("number") BigDecimal value) {
        return join(value, DataSource.RANDOM_GENERATOR);
    }

    @GetMapping("/csv/{number}")
    public ResponseEntity<BigDecimal> addNumberWithValuefromFile(@PathVariable("number") BigDecimal value) {
        return join(value, DataSource.CSV_FILE);
    }

    private ResponseEntity<BigDecimal> join(BigDecimal value, DataSource source) {
        try {
            final BigDecimal provide = bigDecimalDataProvider.provide(source);
            return ResponseEntity.ok(bigDecimalConnectingService.join(value, provide));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }
}
