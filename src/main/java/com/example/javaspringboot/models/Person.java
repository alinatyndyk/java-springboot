package com.example.javaspringboot.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
@Data
@NoArgsConstructor
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
