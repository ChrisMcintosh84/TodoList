package org.mcralph.backend.data;

import org.mcralph.backend.models.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TodoDataService implements DataAccessInterface<TodoModel> {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoDataService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoModel getById(long id) {
        return todoRepository.getReferenceById(id);
    }

    @Override
    public List<TodoModel> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public long addOne(TodoModel todoModel) {
        return todoRepository.save(todoModel).getId();
    }

    @Override
    public TodoModel updateOne(TodoModel todoModel) {
        return todoRepository.save(todoModel);
    }

    @Override
    public void deleteOne(long id) {
        todoRepository.deleteById(id);
    }
}
