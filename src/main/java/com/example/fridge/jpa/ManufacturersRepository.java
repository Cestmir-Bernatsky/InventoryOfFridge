package com.example.fridge.jpa;

import com.example.fridge.jpa.entity.ManufacturersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturersRepository extends JpaRepository<ManufacturersEntity, Integer> {
}
