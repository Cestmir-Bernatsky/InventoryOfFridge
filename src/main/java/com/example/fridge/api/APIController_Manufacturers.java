package com.example.fridge.api;

import com.example.fridge.controllers.ManufacturersService;
import com.example.fridge.jpa.entity.ManufacturersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class APIController_Manufacturers {

    @Autowired
    private ManufacturersService manufacturersService;

    @GetMapping("/manufacturers")
    public List<ManufacturersEntity> getAllManufacturers(){
        return manufacturersService.getAllManufacturers();
    }

    @GetMapping("/manufacturers/{id}")
    public ManufacturersEntity getManufacturerById(@PathVariable("id") Integer id){
        return manufacturersService.getManufacturerById(id);
    }
}
