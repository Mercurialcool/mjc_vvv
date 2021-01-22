package com.epam.esm.dao;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;

import java.util.List;

public interface CertificateDao{//todo abstract interface to
        List<Certificate> allCertificates() throws DaoException;
        Certificate add(Certificate certificate) throws DaoException;
        void delete(Certificate certificate) throws DaoException;
        void edit(Certificate certificate) throws DaoException;
        Certificate getByName(String name) throws DaoException;
        List<Certificate> getCertificateByTag(Tag tag) throws DaoException;
        List<Certificate> searchCertificateByDescription(String description) throws DaoException;
        List<Certificate> searchCertificateByName(String name) throws DaoException;
    }


