package org.mcralph.backend.services;

import org.mcralph.backend.data.TodoRepository;
import org.mcralph.backend.models.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements TodoServiceInterface{
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoModel> getTodos() {
        return todoRepository.findAll();
    }

    public TodoModel getById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    public TodoModel addTodo(TodoModel todo) {
        return todoRepository.save(todo);
    }

    public TodoModel updateTodo(TodoModel todo) {
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
