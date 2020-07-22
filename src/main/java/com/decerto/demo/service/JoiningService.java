package com.decerto.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface JoiningService<T> {
    T join(T val, T val2);
}
