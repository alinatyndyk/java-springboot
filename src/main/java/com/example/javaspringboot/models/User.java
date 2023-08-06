package com.example.javaspringboot.models;

import com.example.javaspringboot.Enums.ERole;
import com.example.javaspringboot.views.ViewsUser;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewsUser.SL1.class)
    private int id;

    @NotBlank(message = "name is required")
    @Size(min = 3, message = "name must have more than 3 characters")
    @Size(max = 255, message = "name must have less than 255 characters")
    @JsonView({ViewsUser.SL1.class, ViewsUser.NoSL.class})
    private String name;

    @Column(unique = true) // todo add regex expressions
    @Size(min = 3, message = "email must have more than 3 characters")
    @Size(max = 255, message = "email must have less than 255 characters")
    @JsonView({ViewsUser.SL1.class, ViewsUser.NoSL.class})
    private String email;

    private String avatar = null;

    private String password;

    private ERole role;

    private String refreshToken;

    private Boolean isActivated = false;

    public User(String name, String email, String avatar, String password) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.password = password;
    }

    public User(String name, String email, String avatar) { //todo delete constructor
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
