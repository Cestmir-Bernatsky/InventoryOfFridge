package com.example.fridge.jpa.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "fridge")
public class FridgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "idproduct")
    private Integer idproduct;

    @Column(name = "pcs")
    private Integer pcs;

    @Column(name = "added")
    private LocalDate added;

    @Column(name = "idmanufacturer")
    private Integer idmanufacturer;

    @Column(name = "expiration")
    private LocalDate expiration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Integer idproduct) {
        this.idproduct = idproduct;
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

    public Integer getIdmanufacturer() {
        return idmanufacturer;
    }

    public void setIdmanufacturer(Integer idmanufacturer) {
        this.idmanufacturer = idmanufacturer;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }
}
