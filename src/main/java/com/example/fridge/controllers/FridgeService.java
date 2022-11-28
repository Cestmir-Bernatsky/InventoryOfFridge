package com.example.fridge.controllers;


import com.example.fridge.jpa.FridgeRepository;
import com.example.fridge.jpa.entity.FridgeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FridgeService {

    @Autowired
    private FridgeRepository fridgeRepository;

    public List<FridgeEntity> getAll(){
        return fridgeRepository.findAll();
    }

    public FridgeEntity getById(Integer id){
        return fridgeRepository.getReferenceById(id);
    }

    public List<FridgeEntity> getAllByKusu(Integer pcs){
        return fridgeRepository.findAllByPcs(pcs);
    }

    public List<FridgeEntity> getAllByCategory(String category){
        return fridgeRepository.findAllByCategory(category);
    }

    public void addItem(FridgeEntity fridgeEntity){
        fridgeRepository.save(fridgeEntity);
    }
}
