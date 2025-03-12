package com.example.product_sp_jwt.service;

import java.util.List;
import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> getAll();
    Optional<T> findById(Long id);
    T save(T t);
    void deleteById(Long id);
}
