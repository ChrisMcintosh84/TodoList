package org.mcralph.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mcralph.backend.models.TodoModel;
import org.mcralph.backend.services.TodoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mcralph.backend.TestUtils.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoRestController.class)
public class TestTodoRestController {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoServiceInterface todoService;

    @Test
    public void testGetTodos() throws Exception {
        List<TodoModel> todoModels = new ArrayList<>();
        todoModels.add(new TodoModel(1L, "test", "test", false));
        todoModels.add(new TodoModel(2L, "test2", "test2", false));
        when(todoService.getTodos()).thenReturn(todoModels);
        this.mvc.perform(get("/api/v1/todos/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'name': 'test', 'description': 'test', 'completed': false}, {'id': 2, 'name': 'test2', 'description': 'test2', 'completed': false}]"));
    }

    @Test
    public void testGetTodoById() throws Exception {
        TodoModel todo = new TodoModel(1L, "test", "test", false);
        when(todoService.getById(1L)).thenReturn(todo);
        this.mvc.perform(get("/api/v1/todos/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name': 'test', 'description': 'test', 'completed': false}"));
    }

    @Test
    public void testAddTodo() throws Exception {
        ObjectMapper objectMapper  = new ObjectMapper();

        TodoModel todo = new TodoModel(1, "test", "test", false);
        when(todoService.addTodo(any(TodoModel.class))).thenReturn(todo);

        MvcResult result = mvc.perform(post("/api/v1/todos/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(todo))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println("responseBody: " + responseBody);
        TodoModel savedTodo = objectMapper.readValue(responseBody, TodoModel.class);

        assertNotNull(savedTodo.getId());
        assertEquals(todo, savedTodo);
    }

    @Test
    public void testVerifyAddTodo() throws Exception {
        TodoModel todo = new TodoModel(1, "test", "test", false);
        when(todoService.addTodo(todo)).thenReturn(todo);

        TodoModel savedTodo = todoService.addTodo(todo);

        verify(todoService).addTodo(todo);
        assertNotNull(savedTodo);
        assertEquals(todo, savedTodo);
    }
}
