package com.example.javaspringboot.services;

import com.example.javaspringboot.dao.PersonRepository;
import com.example.javaspringboot.models.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

//    public PersonService(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }
}
