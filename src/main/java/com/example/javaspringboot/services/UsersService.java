package com.example.javaspringboot.services;

import com.example.javaspringboot.dao.UserDAO;
import com.example.javaspringboot.dto.UserDTO;
import com.example.javaspringboot.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private UserDAO userDAO;

    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userDAO.findAll(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<User>> getAllSL1() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("access_token", "hjds76sd767636733267");
        return new ResponseEntity<>(userDAO.findAll(), httpHeaders, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<User> getById(int id) {
        return new ResponseEntity<>(userDAO.findById(id).get(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<User> post(UserDTO userDTO) {
        String name = userDTO.getUserName();
        return new ResponseEntity<>(userDAO.save(new User(name)), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<User>> deleteById(int id) {
        userDAO.deleteById(id);
        return new ResponseEntity<>(userDAO.findAll(), HttpStatus.GONE);
    }

}
