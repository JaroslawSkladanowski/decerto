package com.decerto.demo.services;

import com.decerto.demo.models.DataSource;

public interface DataProvider <T> {
    T provide(DataSource sources);
}
