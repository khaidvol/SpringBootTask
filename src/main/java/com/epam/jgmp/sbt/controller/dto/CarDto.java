package com.epam.jgmp.sbt.controller.dto;

import java.util.Date;

public class CarDto {

    private String brand;
    private String model;
    private String vin;
    private Date manufactureDate;

    public CarDto(String brand, String model, String vin, Date manufactureDate) {
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.manufactureDate = manufactureDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }
}
