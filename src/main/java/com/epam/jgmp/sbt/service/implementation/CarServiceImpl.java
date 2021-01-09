package com.epam.jgmp.sbt.service.implementation;

import com.epam.jgmp.sbt.repository.CarRepository;
import com.epam.jgmp.sbt.repository.model.Car;
import com.epam.jgmp.sbt.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;

  public CarServiceImpl(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  @Override
  public List<Car> getAllCars() {
    return (List<Car>) carRepository.findAll();
  }

  @Override
  public List<Car> getAllCarsByBrand(String brand) {
    return carRepository.findByBrand(brand);
  }

  @Override
  public Car getCarById(Long id) {
    return carRepository.findById(id).orElseGet(null);
  }

  @Override
  public Car addCar(Car car) {
    return carRepository.save(car);
  }

  @Override
  public Car updateCar(Long id, Car car) {
    Optional<Car> existingCar = carRepository.findById(id);
    Car resultCar = null;
    if (existingCar.isPresent()) {
      car.setId(existingCar.get().getId());
      resultCar = carRepository.save(car);
    }
    return resultCar;
  }

  @Override
  public Car deleteCar(Long id) {
    Optional<Car> existingCar = carRepository.findById(id);
    Car resultCar = null;
    if (existingCar.isPresent()) {
      resultCar = existingCar.get();
      carRepository.deleteById(id);
    }
    return resultCar;
  }
}
