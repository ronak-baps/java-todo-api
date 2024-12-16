package com.example.todo.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.todo.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    Optional<User> findByIdAndIsDeletedFalse(ObjectId id);
    Optional<User> findByEmailAndIsDeletedFalse(String email);
}
