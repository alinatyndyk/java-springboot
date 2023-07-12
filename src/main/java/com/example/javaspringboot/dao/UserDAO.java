package com.example.javaspringboot.dao;

import com.example.javaspringboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO  extends JpaRepository<User, Integer> {
    ///
}
