package com.example.fridge.jpa.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idproduct", insertable = false, updatable = false)
    private Integer idproduct;

    @Column(name = "product")
    private String product;

    @ManyToOne
    @JoinColumn(name = "idcategory", referencedColumnName = "idcategory")
    private CategoriesEntity categoryfk;

    public CategoriesEntity getCategoryfk() {
        return categoryfk;
    }

    public Integer getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Integer idproduct) {
        this.idproduct = idproduct;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

}
