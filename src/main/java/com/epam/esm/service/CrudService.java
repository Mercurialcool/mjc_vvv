package com.epam.esm.service;

import com.epam.esm.dao.DaoException;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface CrudService<T> {
    List<T> getByParameters(MultiValueMap<String, String> params) throws  CertificateServiceException, DaoException;
    T add(T t) throws CertificateServiceException, DaoException;
    void delete(T t, Long id) throws CertificateServiceException, DaoException;
    T getByName(String name) throws CertificateServiceException, DaoException;
}
