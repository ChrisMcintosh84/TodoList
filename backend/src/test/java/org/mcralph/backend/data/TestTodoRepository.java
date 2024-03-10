package org.mcralph.backend.data;

import org.junit.jupiter.api.Test;
import org.mcralph.backend.models.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestTodoRepository {
    @Autowired
    TodoRepository todoRepository;

    @Test
    public void testAddGetDelete() {
        TodoModel todo = new TodoModel(1L, "test", "test", false);

        todoRepository.save(todo);

        Iterable<TodoModel> todoModels = todoRepository.findAll();
        assertThat(todoModels).extracting(TodoModel::getName).containsOnly("test");

        todoRepository.deleteAll();
        assertThat(todoRepository.findAll()).isEmpty();
    }
}
