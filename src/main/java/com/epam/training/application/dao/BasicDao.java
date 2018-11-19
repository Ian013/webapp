package com.epam.training.application.dao;

import java.util.List;

public interface BasicDao<T> {
    T getById(int id);
    List<T> getAll();
    Integer saveOrUpdate(T t);
    Integer remove(int id);
}
