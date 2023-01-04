package com.example.fridge.jpa.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "fridge")
public class FridgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pcs")
    private Integer pcs;

    @Column(name = "added")
    private LocalDate added;

    @Column(name = "expiration")
    private LocalDate expiration;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "exp")
    private Integer exp = 0;

    @ManyToOne
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct")
    private ProductsEntity productfk;


    public ProductsEntity getProductfk() {
        return productfk;
    }

    public void setProductfk(ProductsEntity productfk) {
        this.productfk = productfk;
    }

    public void setManufacturerfk(ManufacturersEntity manufacturerfk) {
        this.manufacturerfk = manufacturerfk;
    }

    @ManyToOne
    @JoinColumn(name = "idmanufacturer", referencedColumnName = "idmanufacturer")
    private ManufacturersEntity manufacturerfk;

    public ManufacturersEntity getManufacturerfk() {
        return manufacturerfk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getPcs() {
        return pcs;
    }

    public void setPcs(Integer pcs) {
        this.pcs = pcs;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }
}
