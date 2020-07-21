package com.decerto.demo.controllers;

import com.decerto.demo.models.DataSource;
import com.decerto.demo.services.ConnectingService;
import com.decerto.demo.services.DataProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/numbers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NumericController {

    ConnectingService<BigDecimal> bigDecimalConnectingService;
    DataProvider<BigDecimal> bigDecimalDataProvider;

    @GetMapping("/connect/{number}")
    public BigDecimal addNumbers(@PathVariable("number") BigDecimal value) {
        return bigDecimalConnectingService.connect(value, bigDecimalDataProvider.provide(DataSource.RANDOM_GENERATOR));
    }
}
