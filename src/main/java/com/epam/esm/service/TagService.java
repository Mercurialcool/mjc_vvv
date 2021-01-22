package com.epam.esm.service;

import com.epam.esm.dao.DaoException;
import com.epam.esm.model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findAll() throws CertificateServiceException, DaoException;
    Tag add(Tag tag) throws DaoException;
    boolean delete(Tag tag) throws DaoException;
    Tag getByName(String name);
}
