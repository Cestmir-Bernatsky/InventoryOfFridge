package com.example.fridge.controllers;

import com.example.fridge.jpa.ProductsRepository;
import com.example.fridge.jpa.entity.ProductsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<ProductsEntity> getAllProducts(){
        return productsRepository.findAll();
    }

    public ProductsEntity getProductById(Integer id){
        return productsRepository.getReferenceById(id);
    }
}
