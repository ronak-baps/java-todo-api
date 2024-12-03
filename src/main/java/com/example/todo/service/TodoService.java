package com.example.todo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

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
        todoEntry.setIsDeleted(false);
        todoEntry.setCreatedAt(now());
        todoEntry.setUpdatedAt(now());
        Todo dbTodo = todoRepository.save(todoEntry);
        System.out.println(dbTodo);
        return todoEntry;
    }

    public List<Todo> getTodoList() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isDeleted").is(false));
        return mongoTemplate.find(query, Todo.class);
    }

    public ResponseEntity<?> getTodoById(ObjectId id) {
        Optional<Todo> todo =  todoRepository.findByIdAndIsDeletedFalse(id);
        if(todo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found");
        }
        return ResponseEntity.ok(todo.get());
    } 

    @SuppressWarnings("null")
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

    @SuppressWarnings("null")
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