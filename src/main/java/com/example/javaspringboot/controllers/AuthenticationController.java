package com.example.javaspringboot.controllers;

import com.example.javaspringboot.dto.UserDTO;
import com.example.javaspringboot.models.AuthenticationResponse;
import com.example.javaspringboot.models.LoginRequest;
import com.example.javaspringboot.models.RegisterRequest;
import com.example.javaspringboot.services.AuthenticationService;
import com.example.javaspringboot.services.UsersServiceMySQLImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;
    private UsersServiceMySQLImpl usersServiceMySQL;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestParam("avatar") MultipartFile picture, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password) {
        String fileName = picture.getOriginalFilename();

        File transferDestinationFile = usersServiceMySQL.transferAvatar(picture, fileName);

        RegisterRequest registerRequest = new RegisterRequest(name, email, fileName, password);

        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login (@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
}
