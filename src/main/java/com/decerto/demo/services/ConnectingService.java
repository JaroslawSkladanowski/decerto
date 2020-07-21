package com.decerto.demo.services;

import org.springframework.stereotype.Service;

@Service
public interface ConnectingService <T> {
    T connect(T val, T val2);
}
