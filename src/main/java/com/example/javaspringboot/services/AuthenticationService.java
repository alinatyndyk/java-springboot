package com.example.javaspringboot.services;

import com.example.javaspringboot.Enums.ERole;
import com.example.javaspringboot.dao.UserDAO;
import com.example.javaspringboot.dao.UserRepoMD;
import com.example.javaspringboot.models.AuthenticationResponse;
import com.example.javaspringboot.models.LoginRequest;
import com.example.javaspringboot.models.RegisterRequest;
import com.example.javaspringboot.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private JwtService jwtService;
    private UserDAO userDAO;
    private UserRepoMD userRepoMD;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    public AuthenticationResponse register (RegisterRequest registerRequest) {
        User user = User
                .builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .avatar(registerRequest.getAvatar()) //todo !!!
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(ERole.USER)
                .build();
        String token = jwtService.generateToken(user);
        userDAO.save(user);


        return AuthenticationResponse
                .builder()
                .accessToken(token)
                .build();
    }

    public AuthenticationResponse login (LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        User user = userDAO.findByEmail(loginRequest.getEmail());
        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder().accessToken(token).build();
    }


}
