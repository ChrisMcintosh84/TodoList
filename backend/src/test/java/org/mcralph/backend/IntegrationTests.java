package org.mcralph.backend;

import jakarta.xml.bind.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mcralph.backend.controllers.TodoRestController;
import org.mcralph.backend.models.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationTests {
    @Autowired
    TodoRestController controller;

    @Test
    public void testCreateReadDelete() {
        TodoModel todoModel = new TodoModel(1L, "test", "test", false);

        TodoModel result = controller.addTodo(todoModel).getBody();

        Iterable<TodoModel> todoModels = controller.getTodos().getBody();
        assertThat(todoModels).first().hasFieldOrPropertyWithValue("name", "test");

        controller.deleteTodo(todoModel.getId());
        assertThat(controller.getTodos().getBody()).isEmpty();
    }

    @Test
    public void errorHandlingValidationExceptionThrown() {
        assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> controller.addTodo(null));
    }
}
