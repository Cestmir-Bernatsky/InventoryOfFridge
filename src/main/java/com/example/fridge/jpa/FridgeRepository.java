package com.example.fridge.jpa;

import com.example.fridge.jpa.entity.FridgeEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FridgeRepository extends JpaRepository<FridgeEntity, Integer> {

    List<FridgeEntity> findByIdproduct(Integer id);

}
