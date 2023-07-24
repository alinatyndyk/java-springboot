package com.example.javaspringboot.dao;

import com.example.javaspringboot.models.UserMD;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepoMD extends MongoRepository<UserMD, String> {
    ///
}
