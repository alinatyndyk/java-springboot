package com.example.javaspringboot.models;

import com.example.javaspringboot.views.ViewsCar;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewsCar.SL1.class)
    private int id;

//    @NotBlank(message = "brand is required")
//    @Size(min = 3, message = "brand must have more than 2 characters")
//    @Size(max = 255, message = "name must have less than 255 characters")
    @JsonView({ViewsCar.SL1.class, ViewsCar.SL2.class})
    private String brand;

//    @NotBlank(message = "power is required")
//    @Min(value = 200, message = "Power has to be more than 200")
//    @Max(value = 3000, message = "Power has to be less than 3000")
    @JsonView({ViewsCar.SL1.class, ViewsCar.SL2.class})
    private int power;

    @JsonView({ViewsCar.SL1.class, ViewsCar.SL2.class, ViewsCar.SL3.class})
    private String producer;

    // todo country enum
    public Car(String brand, int power, String producer) {
        this.brand = brand;
        this.power = power;
        this.producer = producer;
    }
}
