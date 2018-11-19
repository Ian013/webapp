package com.epam.training.application.service;

import java.util.List;

public interface BasicService<T> {

    T getById(int id);

    List<T> getAll();

    Integer saveOrUpdate(T t);

    Integer remove(int id);
}
