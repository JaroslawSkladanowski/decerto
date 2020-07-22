package com.decerto.demo.service.impl.numbers;

import com.decerto.demo.model.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;

class NumberProviderTest {

    @Mock
    NumberProvider numberProvider = Mockito.spy(NumberProvider.class);

    @BeforeEach
    public void setup(){
        Mockito.reset(numberProvider);
    }

    @Test
    void shouldReturnNonNullValueIfDataSourceIsCorrect() {
        //given
        final DataSource dataSource = DataSource.RANDOM_GENERATOR;

        //when
        final BigDecimal value = numberProvider.provide(dataSource);

        //then
        Assertions.assertNotNull(value);
    }

}