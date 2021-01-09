package com.epam.jgmp.sbt.integration;

import com.epam.jgmp.sbt.repository.model.Car;
import com.epam.jgmp.sbt.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ControllerIntegrationTest {

    private static final Long ID = 1L;
    private static final String BRAND_FERRARI = "Ferrari";
    private static final String MODEL_FERRARI_SF_90 = "SF90";
    public static final String MODEL_FERRARI_ROMA = "Roma";
    private static final String VIN_FERRARI = "FERRARI0101010101";
    private static final Date DATE = new Date();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarService carService;

    private Car car;
    private List<Car> cars;


    private ObjectMapper objectMapper;
    private ObjectNode objectNode;

    @BeforeEach
    void setUp() {

        objectMapper = new ObjectMapper();
        objectNode = objectMapper.createObjectNode();

        car = carService.addCar(new Car(BRAND_FERRARI, MODEL_FERRARI_SF_90, VIN_FERRARI, DATE));
        cars = Collections.singletonList(car);
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
