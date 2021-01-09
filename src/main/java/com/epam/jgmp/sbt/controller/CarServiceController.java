package com.epam.jgmp.sbt.controller;

import com.epam.jgmp.sbt.controller.dto.CarDto;
import com.epam.jgmp.sbt.repository.model.Car;
import com.epam.jgmp.sbt.service.CarService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/car-service/cars")
public class CarServiceController {

  private final CarService carService;

  public CarServiceController(CarService carService) {
    this.carService = carService;
  }

  @GetMapping(produces = "application/json")
  public @ResponseBody ResponseEntity<?> getAllCars() {

    List<Car> cars = carService.getAllCars();

    return CollectionUtils.isNotEmpty(cars)
        ? ResponseEntity.ok(cars)
        : ResponseEntity.noContent().build();
  }

  @GetMapping(path = "brand/{brand}", produces = "application/json")
  public @ResponseBody ResponseEntity<?> getAllCarsByBrand(
      @PathVariable(name = "brand") String brand) {

    List<Car> cars = carService.getAllCarsByBrand(brand);

    return CollectionUtils.isNotEmpty(cars)
        ? ResponseEntity.ok(cars)
        : ResponseEntity.noContent().build();
  }

  @GetMapping(path = "id/{id}", produces = "application/json")
  public ResponseEntity<?> getCarById(@PathVariable(name = "id") Long id) {

    Car car = carService.getCarById(id);

    return car != null
            ? ResponseEntity.ok(car)
            : ResponseEntity.notFound().build();
  }

  @PostMapping(produces = "application/json")
  public ResponseEntity<?> addCar(@RequestBody CarDto carDto) {

    Car addedCar = carService.addCar(convertToEntity(carDto));

    return addedCar != null
            ? ResponseEntity.ok(addedCar)
            : ResponseEntity.notFound().build();
  }

  @PutMapping(path = "/{id}", produces = "application/json")
  public ResponseEntity<?> updateCar(@PathVariable("id") Long id, @RequestBody CarDto carDto) {

    Car updatedCar = carService.updateCar(id, convertToEntity(carDto));

    return updatedCar != null
            ? ResponseEntity.ok(updatedCar)
            : ResponseEntity.notFound().build();
  }

  @DeleteMapping(path = "/{id}", produces = "application/json")
  public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) {

    Car deletedCar = carService.deleteCar(id);

    return deletedCar != null
            ? ResponseEntity.ok(deletedCar)
            : ResponseEntity.notFound().build();
  }

  private Car convertToEntity(CarDto carDto) {
    return new Car(
        carDto.getBrand(), carDto.getModel(), carDto.getVin(), carDto.getManufactureDate());
  }
}
