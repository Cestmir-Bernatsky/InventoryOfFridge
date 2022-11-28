package com.example.fridge.jpa;

import com.example.fridge.jpa.entity.FridgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FridgeRepository extends JpaRepository<FridgeEntity, Integer> {

    public List<FridgeEntity> findAllByPcs(Integer pcs);

    public List<FridgeEntity> findAllByCategory(String category);

    public List<FridgeEntity>

}
