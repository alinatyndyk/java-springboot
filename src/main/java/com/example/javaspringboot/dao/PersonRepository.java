package com.example.javaspringboot.dao;

import com.example.javaspringboot.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface PersonRepository extends MongoRepository<Person, String> {
}
