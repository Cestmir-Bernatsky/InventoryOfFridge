package com.example.fridge.jpa;

import com.example.fridge.jpa.entity.FridgeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FridgeRepository extends JpaRepository<FridgeEntity, Integer> {

    @Query(value = "SELECT id, product, category, pcs, added, expiration, idmanufacturer, fridge.idproduct, barcode, exp\n" +
            "FROM fridge\n" +
            "JOIN  products ON fridge.idproduct = products.idproduct\n" +
            "JOIN categories ON products.idcategory = categories.idcategory", nativeQuery = true)
    List<FridgeEntity> mainTable();

    FridgeEntity getByBarcode (String barcode);

    List<FridgeEntity> findAllByProductfkCategoryfkCategory(String category);

    List<FridgeEntity> findAllByProductfkProduct(String product);




}
