package com.example.javaspringboot.dao;

import com.example.javaspringboot.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDAO  extends JpaRepository<Car, Integer> {
    List<Car> findByBrand(String brand);
    List<Car> findByPower(int power);
}
