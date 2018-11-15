package com.epam.training.application.dao;

import java.util.List;

public interface Dao<T> {

    T getById(long id);

    List<T> getAll();

    void save(T t);

    int update(T t, String[] params);

    void delete(T t);

     boolean create(T t);
}
