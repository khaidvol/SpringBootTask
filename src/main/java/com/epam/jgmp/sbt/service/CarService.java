package com.epam.jgmp.sbt.service;

import com.epam.jgmp.sbt.repository.model.Car;

import java.util.List;

public interface CarService {

  /**
   * Find all Cars
   *
   * @return {@link List<Car>}
   */
  List<Car> getAllCars();

  /**
   * Find all Cars by brand
   *
   * @param brand {@link Car} brand
   * @return {@link List<Car>}
   */
  List<Car> getAllCarsByBrand(String brand);

  /**
   * Get Car
   *
   * @param id {@link Car} id
   * @return existing {@link Car} object or <i>null</i> if {@link Car} with specified id was not
   *     found
   */
  Car getCarById(Long id);

  /**
   * Add Car
   *
   * @param car {@link Car}
   * @return {@link Car} object
   */
  Car addCar(Car car);

  /**
   * update Car
   *
   * @param id {@link Car} id
   * @param car {@link Car}
   * @return {@link Car} object
   */
  Car updateCar(Long id, Car car);

  /**
   * Delete Car
   *
   * @param id {@link Car} id
   * @return existing {@link Car} object or <i>null</i> if {@link Car} with specified id was not
   *     found
   */
  Car deleteCar(Long id);
}
