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
    private CategoriesService fridgeController;

//    @PostMapping("/items")
//    public void addItem(){
//        CategoriesEntity fe = new CategoriesEntity();
//        fe.setCategory("ovoce");
//        fe.setPcs(5);
//        fe.setProduct("pomeranc");
//        fridgeController.addItem(fe);
//    }

    @GetMapping("/categories")
    public List<CategoriesEntity> getAll(){
        return fridgeController.getAll();
    }

    @GetMapping("/categories/{id}")
    public CategoriesEntity getById(@PathVariable("id") Integer id){
        return fridgeController.getById(id);
    }


//    @GetMapping("/categories/kusu/{pcs}")
//    public List<CategoriesEntity> getAllByKusu(@PathVariable("pcs") Integer pcs){
//        return fridgeController.getAllByKusu(pcs);
//    }

//    @GetMapping("/items/category/{category}")
//    public List<CategoriesEntity> getAllByCategory(@PathVariable("category") String category){
//        return fridgeController.getAllByCategory(category);
//    }


    //@PatchMapping


}
