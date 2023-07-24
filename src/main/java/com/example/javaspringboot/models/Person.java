package com.example.javaspringboot.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document()
@Data
public class Person {
        @Id
        private String id;
        private String name;
        private String email;

        // getters and setters

        public Person(String name, String email) {
                this.name = name;
                this.email = email;
        }
}
