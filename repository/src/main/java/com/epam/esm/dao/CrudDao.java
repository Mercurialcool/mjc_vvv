package com.epam.esm.dao;

import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for CRUD operations of Certificate/Tag entity
 * @param <T> Generic parameter for certificate and tag
 */

public interface CrudDao<T extends Entity<ID>, ID extends Serializable> {

    /**
     * Shows all Certificates/Tags
     * @return list of required Certificates/Tags
     * @throws DaoException
     */
    List<T> getAll() throws DaoException;

    /**
     * Creates new Certificate/Tag
     * @param t generic parameter
     * @return object, according to type
     * @throws DaoException
     */
    T add(T t) throws DaoException;

    /**
     * Removes Certificate/Tag
     * @param t generic parameter
     * @throws DaoException
     * @return
     */
    Certificate delete(T t) throws DaoException;

    /**
     * Searches Certificate/Tag with a part of name/description(Certificate) or name(Tag)
     * @param searchQuery Map of parameters
     * @return list, according to query
     * @throws DaoException
     */
    List<T> getByParameters(SearchQuery searchQuery) throws DaoException;

    /**
     * Updates Certificate/Tag with given field changes
     * @param t generic parameter
     * @throws DaoException
     * @return
     */
    Certificate edit(T t) throws DaoException;

    /**
     * Searches Certificate/Tag by name
     * @param name name of Certificate/Tag
     * @return Certificate/Tag
     * @throws DaoException
     */
    T getByName(String name) throws DaoException;

    /**
     * Searches Certificate/Tag by ID
     * @param id ID of Certificate/Tag
     * @return
     * @throws DaoException
     */
    T getById(Long id) throws DaoException;
}
