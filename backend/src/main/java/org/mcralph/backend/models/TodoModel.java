package org.mcralph.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class TodoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id = 0L;
    String name = "";
    String description = "";
    Boolean completed = false;

    public TodoModel() {}

    public TodoModel(long id, String name, String description, Boolean completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "TodoModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoModel todoModel = (TodoModel) o;
        return Objects.equals(id, todoModel.id) && Objects.equals(name, todoModel.name) && Objects.equals(description, todoModel.description) && Objects.equals(completed, todoModel.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, completed);
    }
}
