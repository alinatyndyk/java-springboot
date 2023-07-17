package com.example.javaspringboot.controllers;

import com.example.javaspringboot.dto.UserDTO;
import com.example.javaspringboot.models.User;
import com.example.javaspringboot.services.UsersService;
import com.example.javaspringboot.views.ViewsUser;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "users")
public class UserController {

//    @Autowired
    private UsersService usersService;

//    public UserController(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    } // all args constructor

    @GetMapping()
    @JsonView(ViewsUser.NoSL.class)
    public ResponseEntity<List<User>> getAll() {
        return usersService.getAll();
    }

    @GetMapping("/sl1")
    @JsonView(ViewsUser.SL1.class)
    public ResponseEntity<List<User>> getAllSL1() {
        return usersService.getAllSL1();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        return usersService.getById(id);
    }

//    @PostMapping()
//    public ResponseEntity<User> post(@RequestBody @Valid User user) {
//        return new ResponseEntity<>(userDAO.save(user), HttpStatus.ACCEPTED);
//    }

    @PostMapping()
    public ResponseEntity<User> post(@RequestBody UserDTO userDTO) {
        return usersService.post(userDTO);
    }

    @DeleteMapping()
    public ResponseEntity<List<User>> deleteById(@RequestParam("id") int id) {
        return usersService.deleteById(id);
    }

}
