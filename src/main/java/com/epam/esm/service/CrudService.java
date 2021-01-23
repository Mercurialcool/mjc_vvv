package com.epam.esm.service;

import com.epam.esm.dao.DaoException;

import java.util.List;

public interface CrudService<T> {
    List<T> getAll() throws  CertificateServiceException, DaoException;
    T add(T t) throws CertificateServiceException, DaoException;
    boolean delete(T t) throws CertificateServiceException, DaoException;
    T getByName(String name) throws CertificateServiceException, DaoException;
}
