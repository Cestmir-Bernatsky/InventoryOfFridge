package com.example.fridge.controllers;


import com.example.fridge.jpa.CategoriesRepository;
import com.example.fridge.jpa.entity.CategoriesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository fridgeRepository;

    public List<CategoriesEntity> getAll(){
        return fridgeRepository.findAll();
    }

    public CategoriesEntity getById(Integer id){
        return fridgeRepository.getReferenceById(id);
    }

//    public List<CategoriesEntity> getAllByKusu(Integer pcs){
//        return fridgeRepository.findAllByPcs(pcs);
//    }

//    public List<CategoriesEntity> getAllByCategory(String category){
//        return fridgeRepository.findAllByCategory(category);
//    }

    public void addItem(CategoriesEntity fridgeEntity){
        fridgeRepository.save(fridgeEntity);
    }
}
