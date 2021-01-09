package com.epam.jgmp.sbt.repository;

import com.epam.jgmp.sbt.repository.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

  List<Car> findByBrand(String brand);
}
