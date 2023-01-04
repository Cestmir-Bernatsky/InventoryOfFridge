package com.example.fridge.api;


import com.example.fridge.controllers.CategoriesService;
import com.example.fridge.controllers.ManufacturersService;
import com.example.fridge.jpa.entity.CategoriesEntity;
import com.example.fridge.jpa.entity.ManufacturersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class APIController_Categories {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/categories")
    public List<CategoriesEntity> getAll(){
        return categoriesService.getAll();
    }

    @GetMapping("/categories/{id}")
    public CategoriesEntity getById(@PathVariable("id") Integer id){
        return categoriesService.getById(id);
    }

    @GetMapping("/productsByCat/{category}")
    public List<CategoriesEntity> getProductsByCategory(@PathVariable("category") String category){
        return categoriesService.getProductsByCategory(category);
    }

}
