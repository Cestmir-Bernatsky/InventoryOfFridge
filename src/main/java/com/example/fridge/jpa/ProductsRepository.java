package com.example.fridge.jpa;

import com.example.fridge.jpa.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Integer> {


    public List<ProductsEntity> findByCategoryfkCategory(String category);

    public ProductsEntity findByIdproductAndCategoryfkIdcategory(Integer idproduct, Integer idcategory);
}
