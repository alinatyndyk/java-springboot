package com.example.javaspringboot.services;

import com.example.javaspringboot.dao.UserDAO;
import com.example.javaspringboot.dto.UserDTO;
import com.example.javaspringboot.models.User;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersServiceMySQLImpl implements UsersService {

    private UserDAO userDAO;

    public ResponseEntity<List<UserDTO>> getAll() {

        List<UserDTO> responseUsers = new ArrayList<>();
        List<User> users = userDAO.findAll();
        users.forEach(userMD -> responseUsers.add(new UserDTO(
                userMD.getName(),
                userMD.getEmail(),
                userMD.getAvatar())));
        return new ResponseEntity<>(responseUsers, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<UserDTO>> getAllSL1() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("access_token", "hjds76sd767636733267");
        List<UserDTO> responseUsers = new ArrayList<>();
        List<User> users = userDAO.findAll();
        users.forEach(userMD -> responseUsers.add(new UserDTO(
                userMD.getName(),
                userMD.getEmail(),
                userMD.getAvatar())));
        return new ResponseEntity<>(responseUsers, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<UserDTO> getById(String id) {
        User user = userDAO.findById(Integer.parseInt(id)).get();
        return new ResponseEntity<>(new UserDTO(
                user.getName(),
                user.getEmail(),
                user.getAvatar()
        ), HttpStatus.ACCEPTED);
    }

    public UserDTO post(UserDTO userDTO) {  //todo tdo
        User user = userDAO.save(new User(userDTO.getUserName(), userDTO.getEmail(), userDTO.getPicture()));
        return new UserDTO(user.getName(), user.getEmail(), user.getAvatar());
    }

    @SneakyThrows
    public File transferAvatar(MultipartFile picture, String originalFileName) {
        String path = System.getProperty("user.home") + File.separator + "springboot-lib" + File.separator + originalFileName;
        File transferDestinationFile = new File(path);
        picture.transferTo(transferDestinationFile);
        return transferDestinationFile;
    }

    public ResponseEntity<String> deleteById(String id) {
        userDAO.deleteById(Integer.valueOf(id));
        return new ResponseEntity<>("Success.User_deleted", HttpStatus.GONE);
    }

    public ResponseEntity<User> getByEmail(String email) {
        return new ResponseEntity<>(userDAO.findByEmail(email), HttpStatus.GONE);
    }

}
