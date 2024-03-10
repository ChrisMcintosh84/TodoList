package org.mcralph.backend.data;

import java.util.List;

public interface DataAccessInterface <T> {
    public T getById(long id);
    public List<T> getAll();
    public long addOne(T t);
    public T updateOne(T t);
    public void deleteOne(long id);
}
