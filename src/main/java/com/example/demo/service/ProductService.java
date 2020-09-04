package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Product;


public interface ProductService {

    Iterable<Product> findAll();

    List<Product> search(String term);

    Optional<Product> findOne(Integer id);

    void save(Product contact);

    void delete(Integer id);

}