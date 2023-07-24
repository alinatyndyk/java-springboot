package com.example.javaspringboot.controllers;

import com.example.javaspringboot.Enums.EMail;
import com.example.javaspringboot.mail.FMService;
import com.example.javaspringboot.mail.MailerService;
import com.example.javaspringboot.models.User;
import com.example.javaspringboot.services.UsersService;
import com.example.javaspringboot.views.ViewsUser;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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

    private UsersService usersService;
    private MailerService mailerService;
    private FMService fmService;
//    private UserMongoDAO userMongoDAO;

//    public UserController(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    } // all args constructor


    @SneakyThrows
    @GetMapping()
    @JsonView(ViewsUser.NoSL.class)
    public ResponseEntity<List<User>> getAll() {


        mailerService.sendEmail("alinatyndyk777@gmail.com", EMail.USERS, new HashMap<>());

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

//    @PostMapping()
//    public void post(@RequestBody UserDTO userDTO) {
//        System.out.println(userDTO);
//        System.out.println(userDTO.getEmail());
//        mailerService.sendEmail(userDTO.getEmail());
//        return usersService.post(userDTO);
//    }

    @SneakyThrows
    @PostMapping("/save")
    public ResponseEntity<User> post1(@RequestParam("picture") MultipartFile picture, @RequestParam("userName") String userName, @RequestParam("email") String email) {

        String fileName = picture.getOriginalFilename();

        User user = new User(userName, email, fileName);
//        UserMD userMongo = new UserMD(userName, email, fileName);

        File transferDestinationFile = usersService.transferAvatar(picture, fileName);

        Map<String, Object> variables = new HashMap<>();
        variables.put("name", user.getName());

        fmService.sendEmailWithAttachment("haisicraisi@gmail.com", EMail.WELCOME, variables, transferDestinationFile);

        return usersService.post1(user);
    }


    @DeleteMapping()
    public ResponseEntity<List<User>> deleteById(@RequestParam("id") int id) {
        return usersService.deleteById(id);
    }

}
