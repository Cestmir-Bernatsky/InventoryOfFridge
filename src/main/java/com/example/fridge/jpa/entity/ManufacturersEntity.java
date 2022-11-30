package com.example.fridge.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "manufacturers")
public class ManufacturersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idmanufacturer")
    private Integer idmanufacturer;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "country")
    private String country;

    public Integer getIdmanufacturer() {
        return idmanufacturer;
    }

    public void setIdmanufacturer(Integer idmanufacturer) {
        this.idmanufacturer = idmanufacturer;
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
