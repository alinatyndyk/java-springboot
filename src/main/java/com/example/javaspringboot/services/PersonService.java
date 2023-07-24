package com.example.javaspringboot.services;

import com.example.javaspringboot.dao.PersonRepository;
import com.example.javaspringboot.models.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;

    public void savePerson(Person person) {
        personRepository.save(person);
    }
}
