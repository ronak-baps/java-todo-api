package com.example.todo.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.entity.Todo;
import com.example.todo.service.TodoService;

@RestController
@RequestMapping
public class TodoController {
    
    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getTodoList() {
        List<Todo> data = todoService.getTodoList();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }



    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo task) {
        Todo dbTodo = todoService.addTodo(task);
        return dbTodo;
    }
    
    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable ObjectId id) {
        ResponseEntity<?> data = todoService.getTodoById(id);
        return data;
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable ObjectId id, @RequestBody Todo updateTodo) {
        ResponseEntity<?> data = todoService.updateTodo(id, updateTodo);
        return data;
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable ObjectId id) {
        return todoService.deleteTodo(id);
    }
}
