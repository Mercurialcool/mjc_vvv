package com.epam.esm.dao.impl;

import com.epam.esm.dao.*;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Entity;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<T extends Entity<ID>, ID extends Serializable>
        implements CrudDao<T, ID> {

    private EntityManager entityManager;

    public AbstractDao() {
    }

    public AbstractDao(EntityManager entityManager) {
        this.entityManager = entityManager;;
    }

    @Override
    public List<T> getAll() throws DaoException {
        return null;
    }

    @Override
    public T add(T t) throws DaoException {
        entityManager.persist(t);
        return null;
    }

    @Override
    public Certificate delete(T t) throws DaoException {
        return null;
    }

    @Override
    public List<T> getByParameters(SearchQuery searchQuery) throws DaoException {
        return null;
    }

    @Override
    public Certificate edit(T t) throws DaoException {
        return null;
    }

    @Override
    public T getByName(String name) throws DaoException {
        return null;
    }

    @Override
    public T getById(Long id) throws DaoException {
        return null;
    }
}
