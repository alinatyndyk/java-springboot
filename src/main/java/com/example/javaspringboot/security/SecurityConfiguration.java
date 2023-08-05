package com.example.javaspringboot.security;

import com.example.javaspringboot.Enums.ERole;
import com.example.javaspringboot.filters.JWTAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(configurer -> configurer.disable())
                .authorizeHttpRequests(matcherRegistry ->
                        matcherRegistry
//                                .requestMatchers("/api/v1/auth/**")
                                .requestMatchers("/users").hasAnyAuthority("USER") //todo permissions
//                                .authenticated()
                                .anyRequest()
                                .permitAll()
                )
                .sessionManagement(managementConfigurer -> managementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


}
