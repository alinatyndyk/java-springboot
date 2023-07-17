package com.example.javaspringboot.controllers;

import com.example.javaspringboot.dto.CarDTO;
import com.example.javaspringboot.models.Car;
import com.example.javaspringboot.services.CarsService;
import com.example.javaspringboot.views.ViewsCar;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "cars")
public class CarController {

    //    @Autowired
    private CarsService carsService;

    @GetMapping()
    @JsonView(ViewsCar.SL3.class)
    public ResponseEntity<List<Car>> getAll() {
        return carsService.getAll();
    }

    @GetMapping("/brands/{brand}")
    @JsonView(ViewsCar.SL2.class)
    public ResponseEntity<List<Car>> getByBrand(@PathVariable("brand") String brand) {
        return carsService.getByBrand(brand);
    }

    @GetMapping("/power/{power}")
    @JsonView(ViewsCar.SL2.class)
    public ResponseEntity<List<Car>> getByPower(@PathVariable("power") int power) {
        return carsService.getByPower(power);
    }

    @GetMapping("/{id}")
    @JsonView(ViewsCar.SL1.class)
    public ResponseEntity<Car> getById(@PathVariable("id") int id) {
        return carsService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<Car> post(@RequestBody CarDTO car) {
        return carsService.post(car);
    }

    @DeleteMapping()
    public ResponseEntity<List<Car>> deleteById(@RequestParam("id") int id) {
        return carsService.deleteById(id);
    }

}
