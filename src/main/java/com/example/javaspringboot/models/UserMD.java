package com.example.javaspringboot.models;

import com.example.javaspringboot.views.ViewsUser;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class UserMD {

    @Id
    @JsonView(ViewsUser.SL1.class)
    private int id;

    @NotBlank(message = "name is required")
    @Size(min = 3, message = "name must have more than 3 characters")
    @Size(max = 255, message = "name must have less than 255 characters")
    @JsonView({ViewsUser.SL1.class, ViewsUser.NoSL.class})
    private String name;

    @Size(min = 8, message = "email must have more than 8 characters")
    @Size(max = 30, message = "email must have less than 20 characters")
    @JsonView({ViewsUser.SL1.class, ViewsUser.NoSL.class})
    private String email;

    private String avatar = null;

    private Boolean isActivated = false;

    public UserMD(String name, String email, String avatar) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

    public UserMD(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
