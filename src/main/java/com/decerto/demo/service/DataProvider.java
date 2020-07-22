package com.decerto.demo.service;

import com.decerto.demo.model.DataSource;

public interface DataProvider <T> {
    T provide(DataSource sources);
}
