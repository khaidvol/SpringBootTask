package com.epam.jgmp.sbt.repository.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "car")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "brand")
  private String brand;

  @Column(name = "model")
  private String model;

  @Column(name = "vin")
  private String vin;

  @Column(name = "manufacture_date")
  private Date manufactureDate;

  public Car() {}

  public Car(String brand, String model, String vin, Date manufactureDate) {
    this.brand = brand;
    this.model = model;
    this.vin = vin;
    this.manufactureDate = manufactureDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  @Override
  public String toString() {
    return "Car{"
        + "id="
        + id
        + ", brand='"
        + brand
        + '\''
        + ", model='"
        + model
        + '\''
        + ", vin='"
        + vin
        + '\''
        + ", manufactureDate="
        + manufactureDate
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Car car = (Car) o;

    if (id != null ? !id.equals(car.id) : car.id != null) return false;
    if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
    if (model != null ? !model.equals(car.model) : car.model != null) return false;
    if (vin != null ? !vin.equals(car.vin) : car.vin != null) return false;
    return manufactureDate != null
        ? manufactureDate.equals(car.manufactureDate)
        : car.manufactureDate == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (brand != null ? brand.hashCode() : 0);
    result = 31 * result + (model != null ? model.hashCode() : 0);
    result = 31 * result + (vin != null ? vin.hashCode() : 0);
    result = 31 * result + (manufactureDate != null ? manufactureDate.hashCode() : 0);
    return result;
  }
}
