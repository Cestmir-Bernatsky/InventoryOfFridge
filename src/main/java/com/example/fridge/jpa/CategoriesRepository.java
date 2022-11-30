package com.example.fridge.jpa;

import com.example.fridge.jpa.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Integer> {

    //public List<CategoriesEntity> findAllByPcs(Integer pcs);

    //public List<CategoriesEntity> findAllByCategory(String category);

    //public List<FridgeEntity>

}
