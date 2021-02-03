package com.epam.esm.service;

import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.service.exception.ServiceException;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * Interface for CRUD service operations at Certificate/Tag entity
 * @param <T> generic type for service
 */

public interface CrudService<T> {
    /**
     * Gets Certificate/Tag by part of name/description at a service layer
     * @param params Parameters to search by
     * @return List of Certificates/Tags
     * @throws ServiceException
     * @throws DaoException
     */
    List<T> getByParameters(MultiValueMap<String, String> params) throws ServiceException, DaoException;

    /**
     * Checks Certificate/Tag at null and adds new Certificate/Tag at a service layer
     * @param t object to add
     * @return Object to be added at DAO layer
     * @throws ServiceException
     * @throws DaoException
     */
    T add(T t) throws ServiceException, DaoException;

    /**
     * Sets and ID of a Certificate/Tag to be deleted at service layer
     * @param t Certificate/Tag to be deleted
     * @param id Certificate/Tag's id
     * @throws ServiceException
     * @throws DaoException
     */
    void delete(T t, Long id) throws ServiceException, DaoException;

    /**
     * Searches Certificate/Tag by name at a service layer
     * @param name Parameter of search
     * @return Certificate/Tag
     * @throws ServiceException
     * @throws DaoException
     */
    T getByName(String name) throws ServiceException, DaoException;
}
