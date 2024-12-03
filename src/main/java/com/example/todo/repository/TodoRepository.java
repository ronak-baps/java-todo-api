package com.example.todo.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.todo.entity.Todo;

public interface TodoRepository extends MongoRepository<Todo, ObjectId> {
    Optional<Todo> findByIdAndIsDeletedFalse(ObjectId id);
}
