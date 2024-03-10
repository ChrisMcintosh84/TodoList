package org.mcralph.backend.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mcralph.backend.data.TodoRepository;
import org.mcralph.backend.models.TodoModel;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    private TodoService todoService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTodos() {
        List<TodoModel> todoList = new ArrayList<>();
        TodoModel todo = new TodoModel(1L, "test", "test", false);
        TodoModel todo2 = new TodoModel(2L, "test", "test", false);
        todoList.add(todo);
        todoList.add(todo2);
        when(todoRepository.findAll()).thenReturn(todoList);

        todoService = new TodoService(todoRepository);

        List<TodoModel> returnedList = todoService.getTodos();
        assertEquals(2, returnedList.size());
        verify(todoRepository, times(1)).findAll();
    }

    @Test
    void testAddTodo() {
        TodoModel todo = new TodoModel(1L, "test", "test", false);

        todoService = new TodoService(todoRepository);

        todoService.addTodo(todo);
        verify(todoRepository, times(1)).save(todo);
    }
}
