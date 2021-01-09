package com.epam.jgmp.sbt.repository;

import com.epam.jgmp.sbt.repository.model.Car;
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
public class CarRepositoryTest {

    private static final String BRAND_FERRARI = "Ferrari";
    private static final String MODEL_FERRARI_SF_90 = "SF90";
    private static final String VIN_FERRARI = "FERRARI0101010101";

    @Autowired private CarRepository carRepository;
    private Car car;
    private List<Car> cars;

  @BeforeEach
  void setUp() {
      car = carRepository.save(new Car(BRAND_FERRARI, MODEL_FERRARI_SF_90, VIN_FERRARI, new Date()));
      cars = Collections.singletonList(car);
  }

    @Test
    void findAllTest() {
        Assertions.assertEquals(cars, carRepository.findAll());
    }

    @Test
    void findByBrandTest() {
        Assertions.assertEquals(cars, carRepository.findByBrand(BRAND_FERRARI));
    }

    @Test
    void findByIdTest() {
        Assertions.assertEquals(car, carRepository.findById(car.getId()).get());
    }

    @Test
    void saveTest() {
        Assertions.assertEquals(car, carRepository.save(car));
    }

    @Test
    void deleteTest() {
        carRepository.deleteById(car.getId());
    Assertions.assertEquals(Collections.emptyList(), carRepository.findAll());
    }
}