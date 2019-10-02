package com.litse.dbservice.service;

import com.litse.dbservice.entity.Product;
import com.litse.dbservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> orderProducts(){
        return productRepository.findAll().stream().sorted().collect(Collectors.toList());
    }

    public List<Product> filterProducts(){
        return productRepository.findAll().stream().filter(Product::isStatus).collect(Collectors.toList());
    }



}
