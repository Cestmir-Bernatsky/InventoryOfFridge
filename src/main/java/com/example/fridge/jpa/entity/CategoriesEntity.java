package com.example.fridge.jpa.entity;


import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name="categories")
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idcategory")
    private Integer idcategory;
    @Column(name = "category")
    private String category;

    public Integer getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Integer idcategory) {
        this.idcategory = idcategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
