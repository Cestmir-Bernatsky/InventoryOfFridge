package com.example.fridge.jpa.entity;


import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name="categories")
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_category")
    private Integer id_category;
    @Column(name = "category")
    private String category;

    public Integer getId_category() {
        return id_category;
    }

    public void setId_category(Integer id_category) {
        this.id_category = id_category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
