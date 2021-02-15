package com.epam.esm.service;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.exception.ServiceException;

import java.util.List;

/**
 * Interface for CertificateService entity
 */
public interface CertificateService extends CrudService<Certificate> {

    /**
     * Updates Certificate at a service layer
     * @param certificate Object
     * @param id ID of a certificate
     * @throws ServiceException
     */
    Certificate update(Certificate certificate, Long id) throws ServiceException;

    /**
     * Searches Certificates by tag
     * @param tag Object to search by
     * @return List of proper certificates
     * @throws ServiceException
     */
    List<Certificate> getCertificateByTag(Tag tag) throws  ServiceException;
}
