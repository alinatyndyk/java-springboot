package com.example.javaspringboot.controllers;

import com.example.javaspringboot.Enums.EMail;
import com.example.javaspringboot.dto.UserDTO;
import com.example.javaspringboot.mail.FMService;
import com.example.javaspringboot.mail.MailerService;
import com.example.javaspringboot.models.User;
import com.example.javaspringboot.services.UsersServiceMongoImpl;
import com.example.javaspringboot.services.UsersServiceMySQLImpl;
import com.example.javaspringboot.views.ViewsUser;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "users")
public class UserController {

    private UsersServiceMySQLImpl usersServiceMySQL;
    private UsersServiceMongoImpl usersServiceMongo;
    private MailerService mailerService;
    private FMService fmService;

//    public UserController(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    } // all args constructor

    @SneakyThrows
    @GetMapping()
    @JsonView(ViewsUser.NoSL.class)
    public ResponseEntity<List<UserDTO>> getAll() {


        mailerService.sendEmail("alinatyndyk777@gmail.com", EMail.USERS, new HashMap<>());

        return usersServiceMySQL.getAll();
    }

    @GetMapping("/sl1")
    @JsonView(ViewsUser.SL1.class)
    public ResponseEntity<List<UserDTO>> getAllSL1() {
        return usersServiceMySQL.getAllSL1();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable("id") int id) {
        return usersServiceMySQL.getById(String.valueOf(id));
    }


    @SneakyThrows
    @PostMapping()
    public ResponseEntity<UserDTO> post1(@RequestParam("picture") MultipartFile picture, @RequestParam("userName") String userName, @RequestParam("email") String email) {

        String fileName = picture.getOriginalFilename();

        UserDTO userDTO = new UserDTO(userName, email, fileName);

        File transferDestinationFile = usersServiceMySQL.transferAvatar(picture, fileName); // todo make static

        Map<String, Object> variables = new HashMap<>();
        variables.put("name", userDTO.getUserName());

        try {
            UserDTO userSQL = usersServiceMySQL.post(userDTO);
            try {
                usersServiceMongo.post(userDTO);
            } catch (RuntimeException e) {
                User user = usersServiceMySQL.getByEmail(userSQL.getEmail()).getBody();
                assert user != null;
                usersServiceMySQL.deleteById(String.valueOf(user.getId()));
                throw new RuntimeException(e.getMessage());
            }
            fmService.sendEmailWithAttachment(userDTO.getEmail(), EMail.WELCOME, variables, transferDestinationFile);
            return new ResponseEntity<>(userSQL, HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    @DeleteMapping()
    public ResponseEntity<String> deleteById(@RequestParam("id") String id) {
        try {
            usersServiceMongo.deleteById(String.valueOf(id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
        return usersServiceMySQL.deleteById(id);
    }

}
