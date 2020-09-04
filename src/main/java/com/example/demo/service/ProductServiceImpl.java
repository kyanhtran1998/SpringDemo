package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository contactRepository;

    @Override
    public Iterable<Product> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public List<Product> search(String term) {
        return contactRepository.findByNameContaining(term);
    }

    @Override
    public Optional<Product> findOne(Integer id) {
		return  contactRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        contactRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
    	contactRepository.deleteById(id);
    }


}