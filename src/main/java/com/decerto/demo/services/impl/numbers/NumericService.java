package com.decerto.demo.services.impl.numbers;

import com.decerto.demo.services.ConnectingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NumericService implements ConnectingService<BigDecimal> {

    @Override
    public BigDecimal connect(BigDecimal val, BigDecimal val2) {
        return val.add(val2);
    }

}
