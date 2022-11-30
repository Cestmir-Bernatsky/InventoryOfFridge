package com.example.fridge.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "manufacturers")
public class ManufacturersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_manufacturer")
    private Integer id_manufacturer;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "country")
    private String country;

    public Integer getId_manufacturer() {
        return id_manufacturer;
    }

    public void setId_manufacturer(Integer id_manufacturer) {
        this.id_manufacturer = id_manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
