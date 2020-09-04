package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByNameContaining(String term);

}