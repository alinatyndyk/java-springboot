package com.example.javaspringboot.services;

import com.example.javaspringboot.dao.UserDAO;
import com.example.javaspringboot.dto.UserDTO;
import com.example.javaspringboot.models.User;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
//@AllArgsConstructor
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
        String email = userDTO.getEmail();
        return new ResponseEntity<>(userDAO.save(new User(name, email)), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<User> post1(User userDTO) {  //todo tdo

        return new ResponseEntity<>(userDAO.save(userDTO), HttpStatus.ACCEPTED);
    }

//    public ResponseEntity<UserMD> post2(UserMD userMongoDTO) {  //todo tdo
//        return new ResponseEntity<>(userMongoDAO.save(userMongoDTO), HttpStatus.ACCEPTED);
//    }

    @SneakyThrows
    public File transferAvatar(MultipartFile picture, String originalFileName) {
        String path = System.getProperty("user.home") + File.separator + "springboot-lib" + File.separator + originalFileName;
        File transferDestinationFile = new File(path);
        picture.transferTo(transferDestinationFile);
        return transferDestinationFile;
    }

    public ResponseEntity<List<User>> deleteById(int id) {
        userDAO.deleteById(id);
        return new ResponseEntity<>(userDAO.findAll(), HttpStatus.GONE);
    }

}
