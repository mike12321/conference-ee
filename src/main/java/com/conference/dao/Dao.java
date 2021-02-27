package com.conference.dao;

public interface Dao<T> {

    T findById(int id);

    void save(T t);

    void delete(int id);
}
