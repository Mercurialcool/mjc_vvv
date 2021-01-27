package com.epam.esm.dao;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;

import java.util.List;

public interface CertificateDao extends CrudDao<Certificate>{
        List<Certificate> getCertificateByTag(Tag tag) throws DaoException;
        List<Certificate> search(String template) throws DaoException;
    }


