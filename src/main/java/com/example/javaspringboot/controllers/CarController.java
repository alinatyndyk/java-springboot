package com.example.javaspringboot.controllers;

import com.example.javaspringboot.dao.CarDAO;
import com.example.javaspringboot.models.Car;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "cars")
public class CarController {

    //    @Autowired
    private CarDAO carDAO;

    @GetMapping()
    public ResponseEntity<List<Car>> getAll() {
        return new ResponseEntity<>(carDAO.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/brands/{brand}")
    public ResponseEntity<List<Car>> getByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(carDAO.findByBrand(brand), HttpStatus.ACCEPTED);
    }

    @GetMapping("/power/{power}")
    public ResponseEntity<List<Car>> getByPower(@PathVariable("power") int power) {
        return new ResponseEntity<>(carDAO.findByPower(power), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id) {
        return new ResponseEntity<>(carDAO.findById(id).get(), HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<Car> post(@RequestBody Car car) {
        return new ResponseEntity<>(carDAO.save(car), HttpStatus.ACCEPTED);
    }

    @DeleteMapping()
    public ResponseEntity<List<Car>> deleteById(@RequestParam("id") int id) {
        carDAO.deleteById(id);
        return new ResponseEntity<>(carDAO.findAll(), HttpStatus.GONE);
    }

}
