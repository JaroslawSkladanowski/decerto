package com.decerto.demo.service.impl.numbers;

import com.decerto.demo.service.JoiningService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NumericService implements JoiningService<BigDecimal> {

    @Override
    public BigDecimal join(BigDecimal val, BigDecimal val2) {
        return val.add(val2);
    }

}
