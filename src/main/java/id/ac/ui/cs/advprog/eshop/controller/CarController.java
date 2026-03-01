package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
class CarController {
    private static final String REDIRECT_LIST_CAR = "redirect:/car/list";

    private final CarService carService;

    CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping("/create")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/create")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carService.create(car);
        return REDIRECT_LIST_CAR;
    }

    @GetMapping("/list")
    public String carListPage(Model model) {
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }

    @GetMapping("/edit/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        return "editCar";
    }

    @PostMapping("/edit")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        carService.update(car.getId(), car);

        return REDIRECT_LIST_CAR;
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam("carId") String carId) {
        carService.deleteCarById(carId);
        return REDIRECT_LIST_CAR;
    }
}