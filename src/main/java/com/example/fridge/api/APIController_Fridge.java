package com.example.fridge.api;

import com.example.fridge.controllers.FridgeService;
import com.example.fridge.jpa.entity.FridgeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class APIController_Fridge {

    @Autowired
    private FridgeService fridgeService;

    @GetMapping("/items")
    public List<FridgeEntity> getAllItems(){
        return fridgeService.getAllItems();
    }

    @GetMapping("/items/{id}")
    public FridgeEntity getItemById(@PathVariable("id") Integer id){
        return fridgeService.getItemById(id);
    }

    @PostMapping("/items")
    public void addItem (@RequestBody FridgeEntity entity){
        fridgeService.saveItem(entity);
    }




}
