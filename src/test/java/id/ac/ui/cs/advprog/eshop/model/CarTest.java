package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CarTest {
    Car car;
    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setId("1");
        this.car.setName("BMW");
        this.car.setQuantity(100);
    }
    @Test
    void testGetProductId() {
        assertEquals("1", this.car.getId());
    }

    @Test
    void testGetProductName() {
        assertEquals("BMW", this.car.getName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.car.getQuantity());
    }
}