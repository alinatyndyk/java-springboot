package com.example.javaspringboot.services;

import com.example.javaspringboot.Enums.ERole;
import com.example.javaspringboot.dao.UserDAO;
import com.example.javaspringboot.dao.UserRepoMD;
import com.example.javaspringboot.models.requests.RefreshRequest;
import com.example.javaspringboot.models.responses.AuthenticationResponse;
import com.example.javaspringboot.models.requests.LoginRequest;
import com.example.javaspringboot.models.requests.RegisterRequest;
import com.example.javaspringboot.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User
                .builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .avatar(registerRequest.getAvatar()) //todo !!!
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(ERole.USER)
                .build();
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);

        userDAO.save(user);

        return AuthenticationResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        System.out.println(loginRequest.getPassword());
        User user = userDAO.findByEmail(loginRequest.getEmail());
        System.out.println(user);
        String access_token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        System.out.println(access_token);

        user.setRefreshToken(refreshToken);
        userDAO.save(user); //todo refresh save to db user.setRT(rt);

        return AuthenticationResponse.builder().accessToken(access_token).refreshToken(refreshToken).build();
    }

    public AuthenticationResponse refresh(RefreshRequest refreshRequest) {
        String refreshToken = refreshRequest.getRefreshToken();
        String username = jwtService.extractUsername(refreshToken);

        User user = userDAO.findByEmail(username);
        String newAccessToken = null;
        String newRefreshToken = null;

        if (user.getRefreshToken().equals(refreshToken)) {
            newAccessToken = jwtService.generateToken(user);
            newRefreshToken = jwtService.generateRefreshToken(user);
            user.setRefreshToken(newRefreshToken);
            userDAO.save(user);
        }

        return AuthenticationResponse.builder().accessToken(newAccessToken).refreshToken(newRefreshToken).build();
    }


}
