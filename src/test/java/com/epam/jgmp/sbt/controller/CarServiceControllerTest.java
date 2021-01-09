package com.epam.jgmp.sbt.controller;

import com.epam.jgmp.sbt.repository.model.Car;
import com.epam.jgmp.sbt.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarServiceController.class)
public class CarServiceControllerTest {

    private static final Long ID = 1L;
    private static final String BRAND_FERRARI = "Ferrari";
    private static final String MODEL_FERRARI_SF_90 = "SF90";
    public static final String MODEL_FERRARI_ROMA = "Roma";
    private static final String VIN_FERRARI = "FERRARI0101010101";
    private static final Date DATE = new Date();

  @Autowired private MockMvc mockMvc;
  @MockBean private CarService carService;

  private Car car;
  private List<Car> cars;


  private ObjectMapper objectMapper;
  private ObjectNode objectNode;

  @BeforeEach
  void setUp() {

      objectMapper = new ObjectMapper();
      objectNode = objectMapper.createObjectNode();

      car = new Car(BRAND_FERRARI, MODEL_FERRARI_SF_90, VIN_FERRARI, DATE);
      car.setId(ID);
      cars = Collections.singletonList(car);

      Mockito.when(carService.getCarById(ID)).thenReturn(car);
      Mockito.when(carService.getAllCarsByBrand(BRAND_FERRARI)).thenReturn(cars);
      Mockito.when(carService.addCar(ArgumentMatchers.any(Car.class))).thenReturn(car);
      Mockito.when(carService.deleteCar(ID)).thenReturn(car);
  }

  @Test
  void getAllCarsTest() throws Exception {
      this.mockMvc
              .perform(get("/v1.0/car-service/cars"))
              .andExpect(status().is2xxSuccessful())
              .andReturn();
  }

  @Test
  void getAllCarsByBrandTest() throws Exception {
      this.mockMvc
              .perform(get("/v1.0/car-service/cars/brand/{brand}", BRAND_FERRARI))
              .andExpect(status().is2xxSuccessful())
              .andReturn();
  }

  @Test
  void getCarByIdTest() throws Exception {
      this.mockMvc
              .perform(get("/v1.0/car-service/cars/id/{id}", ID))
              .andExpect(status().is2xxSuccessful())
              .andReturn();
  }

  @Test
  void addCarTest() throws Exception {

      objectNode.put("brand", BRAND_FERRARI);
      objectNode.put("model", MODEL_FERRARI_ROMA);
      objectNode.put("vin", VIN_FERRARI);
      objectNode.put("manufacture_date", DATE.toString());

      this.mockMvc
              .perform(
                      post("/v1.0/car-service/cars")
                              .contentType(MediaType.APPLICATION_JSON)
                              .content(objectNode.toString()))
              .andExpect(status().isOk())
              .andReturn();
  }

  @Test
  void updateCarTest() throws Exception {

      car.setModel(MODEL_FERRARI_ROMA);
      Mockito.when(carService.updateCar(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Car.class))).thenReturn(car);

      objectNode.put("brand", BRAND_FERRARI);
      objectNode.put("model", MODEL_FERRARI_SF_90);
      objectNode.put("vin", VIN_FERRARI);
      objectNode.put("manufacture_date", DATE.toString());

      this.mockMvc
              .perform(
                      put("/v1.0/car-service/cars/{id}", ID)
                              .contentType(MediaType.APPLICATION_JSON)
                              .content(objectNode.toString()))
              .andExpect(status().isOk())
              .andReturn();
  }

  @Test
  void deleteCarTest() throws Exception {
      this.mockMvc
              .perform(delete("/v1.0/car-service/cars/{id}", ID))
              .andExpect(status().isOk())
              .andReturn();
  }
}
