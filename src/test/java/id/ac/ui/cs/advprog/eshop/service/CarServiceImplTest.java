package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        // No setup needed for this test class
    }

    @Test
    void testCreate() {
        Car car = new Car();
        car.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        when(carRepository.create(car)).thenReturn(car);

        Car createdCar = carService.create(car);

        verify(carRepository).create(car);
        assertEquals("a0f9de46-90b1-437d-a0bf-d0821dde9096", createdCar.getId());
    }

    @Test
    void testFindAll() {
        List<Car> cars = new ArrayList<>();
        Iterator<Car> iterator = cars.iterator();
        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> listOfProducts = carService.findAll();

        verify(carRepository).findAll();
        assertEquals(cars, listOfProducts);
    }

    @Test
    void testEdit() {
        String carId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Car newCar = new Car();
        newCar.setId(carId);
        newCar.setName("BMW");
        carService.update(carId, newCar);
        verify(carRepository, times(1)).update(carId, newCar);
    }

    @Test
    void testDelete() {
        doNothing().when(carRepository).delete("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        carService.deleteCarById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        verify(carRepository, times(1)).delete("a0f9de46-90b1-437d-a0bf-d0821dde9096");
    }

    @Test
    void testFindById() {
        Car car = new Car();
        when(carRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096")).thenReturn(car);

        Car retrievedProduct = carService.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");

        verify(carRepository).findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertEquals(car, retrievedProduct);
    }
}