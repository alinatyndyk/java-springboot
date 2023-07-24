package com.example.javaspringboot.controllers;

import com.example.javaspringboot.models.Person;
import com.example.javaspringboot.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
//@AllArgsConstructor
public class PersonController {
    @Autowired
    private PersonService personService;

//    public PersonController(PersonService personService) {
//        this.personService = personService;
//    }

    @PostMapping("/")
    public void createUser(@RequestBody Person person) {
        personService.savePerson(person);
    }
}
