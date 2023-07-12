package com.example.javaspringboot.controllers;

import com.example.javaspringboot.dao.UserDAO;
import com.example.javaspringboot.models.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "users")
public class UserController {

//    @Autowired
    private UserDAO userDAO;

//    public UserController(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    } // all args constructor

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("access_token", "hjds76sd767636733267");
        return new ResponseEntity<>(userDAO.findAll(), httpHeaders, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userDAO.findById(id).get(), HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<User> post(@RequestBody User user) {
        return new ResponseEntity<>(userDAO.save(user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping()
    public ResponseEntity<List<User>> deleteById(@RequestParam("id") int id) {
        userDAO.deleteById(id);
        return new ResponseEntity<>(userDAO.findAll(), HttpStatus.GONE);
    }

}
