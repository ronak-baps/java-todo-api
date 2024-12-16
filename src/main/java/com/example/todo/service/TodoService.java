package com.example.todo.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;

@Component
public class TodoService {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
    
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Todo addTodo(Todo todoEntry) {
        Todo dbTodo = todoRepository.save(todoEntry);
        return dbTodo;
    }

    public List<Todo> getTodoList() {
        Query query = new Query(Criteria.where("isDeleted").is(false));
        return mongoTemplate.find(query, Todo.class);
    }

    public ResponseEntity<?> getTodoById(ObjectId id) {
        Optional<Todo> todo = todoRepository.findByIdAndIsDeletedFalse(id);
        if(todo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found");
        }
        return ResponseEntity.ok(todo.get());
    } 

    public ResponseEntity<?> updateTodo(ObjectId id, Todo updatedTodo) {
        ResponseEntity<?> dbTodoOptional = getTodoById(id);
        if (dbTodoOptional.getStatusCode().value() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo is not found");
        }
        Todo dbTodo = (Todo) dbTodoOptional.getBody();
        dbTodo.setTask(updatedTodo.getTask());
        dbTodo.setUpdatedAt(now());
        todoRepository.save(dbTodo);
        return ResponseEntity.ok(dbTodo);
    }

    public ResponseEntity<?> deleteTodo(ObjectId id) {
        ResponseEntity<?> dbTodoOptional = getTodoById(id);
        if (dbTodoOptional.getStatusCode().value() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo is not found");
        }
        Todo dbTodo = (Todo) dbTodoOptional.getBody();
        dbTodo.setIsDeleted(true);
        dbTodo.setUpdatedAt(now());
        todoRepository.save(dbTodo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}