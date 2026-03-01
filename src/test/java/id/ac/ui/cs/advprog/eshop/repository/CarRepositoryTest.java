package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    CarRepository carRepository;

    @BeforeEach
    void setUp() {
        this.carRepository = new CarRepositoryImpl();
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setName("BMW");
        car.setQuantity(100);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getId(), savedCar.getId());
        assertEquals(car.getName(), savedCar.getName());
        assertEquals(car.getQuantity(), savedCar.getQuantity());
    }

    @Test
    void testCreateWithNullId() {
        Car car = new Car();
        car.setId(null);
        car.setName("Suzuki");
        car.setQuantity(5);

        carRepository.create(car);
        assertNotNull(car.getId());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car1.setName("Toyota");
        car1.setQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        car2.setName("Honda");
        car2.setQuantity(50);
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car1.getId(), savedCar.getId());
        savedCar = carIterator.next();
        assertEquals(car2.getId(), savedCar.getId());
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testEdit() {
        Car car = new Car();
        car.setId("123e4567-e89b-12d3-a456-556642440000");
        car.setName("BMW");
        car.setQuantity(100);
        carRepository.create(car);

        Car newCarData = new Car();
        newCarData.setName("Toyota");
        newCarData.setQuantity(500);
        carRepository.update(car.getId(), newCarData);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();

        assertEquals(car.getId(), savedCar.getId());
        assertEquals(car.getName(), savedCar.getName());
        assertEquals(car.getQuantity(), savedCar.getQuantity());
    }

    @Test
    void testIfEmpty() {
        Car car = new Car();
        car.setId("123e4567-e89b-12d3-a456-556642440000");
        car.setName("BMW");
        car.setQuantity(100);
        carRepository.update(car.getId(), car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setId("123e4567-e89b-12d3-a456-556642440000");
        car1.setName("Wuling");
        car1.setQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setId("123e4567-e89b-12d3-a456-556642440001");
        car2.setName("Range Rover");
        car2.setQuantity(500);
        carRepository.create(car2);

        Car editCar = new Car();
        editCar.setId(car1.getId());
        editCar.setName("Bis Tayo");
        editCar.setQuantity(300);

        carRepository.update(car1.getId(), editCar);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());

        Car savedCar1 = carIterator.next();
        assertEquals(car1.getId(), savedCar1.getId());
        assertEquals(car1.getName(), savedCar1.getName());
        assertEquals(car1.getQuantity(), savedCar1.getQuantity());

        Car savedCar2 = carIterator.next();
        assertEquals(car2.getId(), savedCar2.getId());
        assertEquals(car2.getName(), savedCar2.getName());
        assertEquals(car2.getQuantity(), savedCar2.getQuantity());

        assertFalse(carIterator.hasNext());
    }

    @Test
    void testDelete() {
        Car car = new Car();
        car.setId("123e4567-e89b-12d3-a456-556642440000");
        car.setName("Odong-odong");
        car.setQuantity(100);
        carRepository.create(car);

        carRepository.delete(car.getId());

        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testDeleteIfEmpty() {
        carRepository.delete("123e4567-e89b-12d3-a456-556642440000");
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testDeleteIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setId("123e4567-e89b-12d3-a456-556642440000");
        car1.setName("Daihatsu");
        car1.setQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setId("123e4567-e89b-12d3-a456-556642440001");
        car2.setName("Suzuki");
        car2.setQuantity(500);
        carRepository.create(car2);

        carRepository.delete(car1.getId());
        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());

        Car savedCar = carIterator.next();
        assertEquals(car2.getId(), savedCar.getId());

        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setId("123e4567-e89b-12d3-a456-556642440001");
        car.setName("Toyota Avanza");
        car.setQuantity(10);
        carRepository.create(car);

        Car result = carRepository.findById("123e4567-e89b-12d3-a456-556642440001");

        assertNotNull(result);
        assertEquals(car.getId(), result.getId());
        assertEquals(car.getName(), result.getName());
    }

    @Test
    void testFindByIdNull() {
        Car car = new Car();
        car.setId(null);
        car.setName("dummy");
        car.setQuantity(1);

        carRepository.create(car);
        assertNull(carRepository.findById("non-exist-id"));
    }

    @Test
    void testEditCarNotAtFirstIndex() {
        Car car1 = new Car();
        car1.setId("123e4567-e89b-12d3-a456-556642440001");
        car1.setName("Mercedez");
        car1.setQuantity(1);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setId("123e4567-e89b-12d3-a456-556642440000");
        car2.setName("Jeep");
        car2.setQuantity(5);
        carRepository.create(car2);

        Car newCarData = new Car();
        newCarData.setName("Brio");
        newCarData.setQuantity(100);
        Car result = carRepository.update(car2.getId(), newCarData);

        assertNotNull(result);
        assertEquals("Brio", result.getName());
    }
}