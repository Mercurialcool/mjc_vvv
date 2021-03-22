package com.epam.esm.dao;

import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;

import java.util.List;

/**
 * Interface for Certificate entity
 *
 */

public interface CertificateDao extends CrudDao<Certificate, Long> {
    /**
     * Finds certificate by tag
     * @param tagIds
     * @return List of certificates with the same tag
     * @throws DaoException
     */
        List<Certificate> getCertificateByTag(List<Long> tagIds) throws DaoException;

}


