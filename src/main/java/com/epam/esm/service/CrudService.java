package com.epam.esm.service;

import com.epam.esm.dao.DaoException;
import com.epam.esm.model.Certificate;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * Interface for CRUD service operations at Certificate/Tag entity
 * @param <T> generic type for service
 */

public interface CrudService<T> {
    List<T> getByParameters(MultiValueMap<String, String> params) throws CertificateServiceException, DaoException;
    T add(T t) throws CertificateServiceException, DaoException;
    void delete(T t, Long id) throws CertificateServiceException, DaoException;
    T getByName(String name) throws CertificateServiceException, DaoException;
}
