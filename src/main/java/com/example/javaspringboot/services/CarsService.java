package com.example.javaspringboot.services;

import com.example.javaspringboot.dao.CarDAO;
import com.example.javaspringboot.dto.CarDTO;
import com.example.javaspringboot.models.Car;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class CarsService {

    private CarDAO carDAO;

    public ResponseEntity<List<Car>> getAll() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("access_token", "hjds76sd767636733267");
        return new ResponseEntity<>(carDAO.findAll(), httpHeaders, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Car> getById(int id) {
        return new ResponseEntity<>(carDAO.findById(id).get(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Car> post(CarDTO carDTO) {
        return new ResponseEntity<>(carDAO.save(
                new Car(carDTO.getBrand(), carDTO.getPower(), carDTO.getProducer())), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Car>> deleteById(int id) {
        carDAO.deleteById(id);
        return new ResponseEntity<>(carDAO.findAll(), HttpStatus.GONE);
    }

    public ResponseEntity<List<Car>> getByBrand(String brand) {
        return new ResponseEntity<>(carDAO.findByBrand(brand), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Car>> getByPower(int power) {
        return new ResponseEntity<>(carDAO.findByPower(power), HttpStatus.ACCEPTED);
    }

}
