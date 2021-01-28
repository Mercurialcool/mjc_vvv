package com.epam.esm.dao;

import org.springframework.util.MultiValueMap;

import java.util.List;

public interface CrudDao<T> {
    List<T> getAll() throws DaoException;
    List<T> getByParameters(MultiValueMap<String, String> params) throws DaoException;
    T add(T t) throws DaoException;
    void delete(T t) throws DaoException;
    void edit(T t) throws DaoException;
    T getByName(String name) throws DaoException;
}
