package com.example.fridge.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.fridge.jpa.FridgeRepository;
import com.example.fridge.jpa.entity.FridgeEntity;
import org.hibernate.usertype.LoggableUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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


    public void saveItem(FridgeEntity entity){

        List<FridgeEntity> itemByProductId = fridgeRepository.findByIdproduct(entity.getIdproduct());

        if(itemByProductId != null){
            for(FridgeEntity item : itemByProductId){
                if(item.getIdmanufacturer() == entity.getIdmanufacturer() && item.getIdproduct() == entity.getIdproduct()){
                    item.setPcs(item.getPcs() + entity.getPcs());
                    fridgeRepository.save(item);
                    System.out.println("YESSS");
                }else{
                    System.out.println("NOOO");
                }
            }
        }
    }
}
