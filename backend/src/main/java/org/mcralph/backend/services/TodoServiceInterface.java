package org.mcralph.backend.services;

import org.mcralph.backend.models.TodoModel;

import java.util.List;

public interface TodoServiceInterface {

    public List<TodoModel> getTodos();
    public TodoModel getById(Long id);
    public TodoModel addTodo(TodoModel todo);
    public TodoModel updateTodo(TodoModel todo);
    public void deleteTodo(Long id);
}
