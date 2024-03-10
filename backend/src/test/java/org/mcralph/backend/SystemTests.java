package org.mcralph.backend;

import org.junit.jupiter.api.Test;
import org.mcralph.backend.controllers.TodoRestController;
import org.mcralph.backend.models.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemTests {
    @Autowired
    private TodoRestController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testCreateReadDelete() {
        String url = "http://localhost:" + port + "/api/v1/todos";

        TodoModel todoModel = new TodoModel(1L, "test", "test", false);
        ResponseEntity<TodoModel> responseEntity = template.postForEntity(url, todoModel, TodoModel.class);

        TodoModel[] todoModels = template.getForObject(url, TodoModel[].class);
        assertThat(todoModels).extracting(TodoModel::getName).containsOnly("test");

        template.delete(url + "/" + responseEntity.getBody().getId());
        assertThat(template.getForObject(url, TodoModel[].class)).isEmpty();

    }
}
