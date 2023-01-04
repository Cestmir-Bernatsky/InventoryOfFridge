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

    public ProductsRepository getProductsRepository() {
        return productsRepository;
    }

    public List<ProductsEntity> getAllProducts(){
        return productsRepository.findAll();
    }

    public ProductsEntity getProductById(Integer id){
        return productsRepository.getReferenceById(id);
    }

    public List<ProductsEntity> getByCat(String cat){
        return productsRepository.findByCategoryfkCategory(cat);
    }

    public ProductsEntity findByIdproductAndCategoryfkIdcategory(Integer idproduct, Integer idcategory)
    {
        return productsRepository.findByIdproductAndCategoryfkIdcategory(idproduct, idcategory);
    }
}
