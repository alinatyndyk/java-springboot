package com.example.javaspringboot.models;

import com.example.javaspringboot.views.ViewsUser;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewsUser.SL1.class)
    private int id;

    @NotBlank(message = "name is required")
    @Size(min = 3, message = "name must have more than 3 characters")
    @Size(max = 255, message = "name must have less than 255 characters")
    @JsonView({ViewsUser.SL1.class, ViewsUser.NoSL.class})
    private String name;

    public User(String name) {
        this.name = name;
    }
}
