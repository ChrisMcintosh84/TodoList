package org.mcralph.backend.controllers;

import org.mcralph.backend.models.TodoModel;
import org.mcralph.backend.services.TodoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoRestController {
    TodoServiceInterface todoService;

    @Autowired
    public TodoRestController(TodoServiceInterface todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TodoModel>> getTodos() {
        return new ResponseEntity<>(todoService.getTodos(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoModel> getById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(todoService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TodoModel> addTodo(@RequestBody TodoModel todo) {

        System.out.println("Incoming todo: " + todo);
        TodoModel todoToSave = todoService.addTodo(todo);
        System.out.println("return from service: " + todoToSave);

        return new ResponseEntity<>(todoToSave, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<TodoModel> updateTodo(@RequestBody TodoModel todo) {
        return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable(name = "id") long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Todo deleted", HttpStatus.OK);
    }
}