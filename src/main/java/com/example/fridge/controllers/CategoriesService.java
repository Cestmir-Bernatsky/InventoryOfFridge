package com.example.fridge.controllers;


import com.example.fridge.jpa.CategoriesRepository;
import com.example.fridge.jpa.entity.CategoriesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<CategoriesEntity> getAll(){
        return categoriesRepository.findAll();
    }

    public CategoriesEntity getById(Integer id){
        return categoriesRepository.getReferenceById(id);
    }


    public List<CategoriesEntity> getProductsByCategory(String category){
        return categoriesRepository.findByCategory(category);
    }


    public void addItem(CategoriesEntity categoryEntity){
        categoriesRepository.save(categoryEntity);
    }
}
