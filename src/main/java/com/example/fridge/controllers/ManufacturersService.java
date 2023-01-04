package com.example.fridge.controllers;

import com.example.fridge.jpa.ManufacturersRepository;
import com.example.fridge.jpa.entity.ManufacturersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManufacturersService {

    @Autowired
    private ManufacturersRepository manufacturersRepository;

    public ManufacturersRepository getManufacturersRepository() {
        return manufacturersRepository;
    }

    public List<ManufacturersEntity> getAllManufacturers (){
        return manufacturersRepository.findAll();
    }

    public ManufacturersEntity getManufacturerById(Integer id){
        return manufacturersRepository.getReferenceById(id);
    }

    public List<ManufacturersEntity> getProductsByManufacturer(Integer id){
        return manufacturersRepository.findProductsByIdmanufacturer(id);
    }

}
