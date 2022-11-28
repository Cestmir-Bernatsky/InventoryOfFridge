package com.example.fridge.api;


import com.example.fridge.controllers.FridgeService;
import com.example.fridge.jpa.entity.FridgeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {

    @Autowired
    private FridgeService fridgeController;

    @PostMapping("/items")
    public void addItem(){
        FridgeEntity fe = new FridgeEntity();
        fe.setCategory("ovoce");
        fe.setPcs(5);
        fe.setProduct("pomeranc");
        fridgeController.addItem(fe);
    }

    @GetMapping("/items")
    public List<FridgeEntity> getAll(){
        return fridgeController.getAll();
    }

    @GetMapping("/items/{id}")
    public FridgeEntity getById(@PathVariable("id") Integer id){
        return fridgeController.getById(id);
    }

    @GetMapping("/items/kusu/{pcs}")
    public List<FridgeEntity> getAllByKusu(@PathVariable("pcs") Integer pcs){
        return fridgeController.getAllByKusu(pcs);
    }

    @GetMapping("/items/category/{category}")
    public List<FridgeEntity> getAllByCategory(@PathVariable("category") String category){
        return fridgeController.getAllByCategory(category);
    }


    //@PatchMapping


}
