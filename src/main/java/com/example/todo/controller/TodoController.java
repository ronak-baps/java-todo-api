package com.example.todo.controller;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
    
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<?> getTodoList() {
        List<Todo> data = todoService.getTodoList();
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<?> addTodo(@RequestBody Todo task) {
        Todo dbTodo = todoService.addTodo(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(dbTodo);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getTodoById(@PathVariable ObjectId id) {
        return todoService.getTodoById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTodo(@PathVariable ObjectId id, @RequestBody Todo updateTodo) {
        return todoService.updateTodo(id, updateTodo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable ObjectId id) {
        return todoService.deleteTodo(id);
    }
}
