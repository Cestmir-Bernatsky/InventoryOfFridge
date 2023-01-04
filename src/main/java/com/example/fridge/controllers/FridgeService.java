package com.example.fridge.controllers;

import com.example.fridge.jpa.FridgeRepository;
import com.example.fridge.jpa.entity.FridgeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FridgeService {

    @Autowired
    private FridgeRepository fridgeRepository;

    public List<FridgeEntity> getAllItems(){
        return fridgeRepository.findAll();
    }



    public FridgeEntity getItemById(Integer id){
        return fridgeRepository.getReferenceById(id);
    }

    public List<FridgeEntity> getMainTable(String cat){
        return fridgeRepository.findAllByProductfkCategoryfkCategory(cat);
    }

    public List<FridgeEntity> getMainTable2(){

        List<FridgeEntity> entities = fridgeRepository.mainTable();
        for(FridgeEntity entity : entities){
            if(entity.getExpiration().isBefore(LocalDate.now())){
                entity.setExp(entity.getPcs());
            }else{
                entity.setExp(0);
            }
        }
        return entities;
    }

    public List<FridgeEntity> getDesProduct(String product){
        return fridgeRepository.findAllByProductfkProduct(product);
    }

    public void deleteProduct(Integer id){
        fridgeRepository.deleteById(id);
    }

    public void updatePcs(Integer id, FridgeEntity item){
        FridgeEntity fridgeEntity = fridgeRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("item with" + id + "doesn't exist"));

        if(item.getPcs() < 1){
            fridgeRepository.delete(fridgeEntity);
        }else{
            fridgeEntity.setPcs(item.getPcs());
            fridgeRepository.save(fridgeEntity);
        }
    }

    public void addEntry(FridgeEntity entity){
        //TODO check

        fridgeRepository.save(entity);
    }

    public FridgeEntity getBarcode(String barcode){
        return fridgeRepository.getByBarcode(barcode);
    }


}
