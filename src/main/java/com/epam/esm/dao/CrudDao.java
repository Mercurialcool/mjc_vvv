package com.epam.esm.dao;

import java.util.List;

public interface CrudDao<T> {
    List<T> getAll() throws DaoException;
    T add(T t) throws DaoException;
    void delete(T t) throws DaoException;
    void edit(T t) throws DaoException;
    T getByName(String name) throws DaoException;
}
