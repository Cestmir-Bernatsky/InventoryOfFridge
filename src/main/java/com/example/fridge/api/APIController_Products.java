package com.example.fridge.api;

import com.example.fridge.controllers.ProductsService;
import com.example.fridge.jpa.entity.ProductsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class APIController_Products {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/products")
    public List<ProductsEntity> getAllProducts(){
        return productsService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductsEntity getProductById(@PathVariable("id") Integer id){
        return productsService.getProductById(id);

    }
}
