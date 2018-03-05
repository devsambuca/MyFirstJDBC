package com.dev.app.dao;

public interface GenericDAO<T> {

    void create(T t);
    T read(long id);
    void update(T t);
    void delete(long id);

}
