package com.epam.jgmp.sbt.service.implementation;

import com.epam.jgmp.sbt.repository.model.Car;
import com.epam.jgmp.sbt.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CarServiceImplTest {

  public static final String BRAND_FERRARI = "Ferrari";
  public static final String MODEL_FERRARI_SF_90 = "SF90";
  public static final String MODEL_FERRARI_ROMA = "Roma";
  public static final String VIN_FERRARI = "FERRARI0101010101";

  @Autowired private CarService carService;
  private Car car;
  private List<Car> cars;

  @BeforeEach
  void setUp() {
    car = carService.addCar(new Car(BRAND_FERRARI, MODEL_FERRARI_SF_90, VIN_FERRARI, new Date()));
    cars = Collections.singletonList(car);
  }

  @Test
  void getAllCarsTest() {
    Assertions.assertEquals(cars, carService.getAllCars());
  }

  @Test
  void getAllCarsByBrandTest() {
    Assertions.assertEquals(cars, carService.getAllCarsByBrand(BRAND_FERRARI));
  }

  @Test
  void getCarByIdTest() {
    Assertions.assertEquals(car, carService.getCarById(car.getId()));
  }

  @Test
  void getNotExistingCarByIdTest() {
    Assertions.assertThrows(NullPointerException.class, () -> carService.getCarById(666L));
  }

  @Test
  void addCarTest() {
    Assertions.assertEquals(car, carService.addCar(car));
  }

  @Test
  void updateCarTest() {
    car.setModel(MODEL_FERRARI_ROMA);
    Assertions.assertEquals(car, carService.updateCar(car.getId(), car));
  }

  @Test
  void deleteCarTest() {
    Assertions.assertEquals(car, carService.deleteCar(car.getId()));
  }
}
