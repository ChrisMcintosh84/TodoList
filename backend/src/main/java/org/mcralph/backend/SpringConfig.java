package org.mcralph.backend;

import org.mcralph.backend.data.DataAccessInterface;
import org.mcralph.backend.data.TodoDataService;
import org.mcralph.backend.data.TodoRepository;
import org.mcralph.backend.models.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class SpringConfig {
    @Autowired
    public TodoRepository todoRepository;

    @Bean(name = "todoDataService")
    @RequestScope
    public DataAccessInterface<TodoModel> getDataService() {
        return new TodoDataService(todoRepository);
    }

}
