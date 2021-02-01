package com.epam.esm.service;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;

import java.util.List;

/**
 * Interface for CertificateService entity
 */
public interface CertificateService extends CrudService<Certificate> {

    /**
     * Updates Certificate at a service layer
     * @param certificate Object
     * @param id ID of a certificate
     * @throws CertificateServiceException
     */
    void update(Certificate certificate, Long id) throws  CertificateServiceException;

    /**
     * Searches Certificates by tag
     * @param tag Object to search by
     * @return List of proper certificates
     * @throws CertificateServiceException
     */
    List<Certificate> getCertificateByTag(Tag tag) throws  CertificateServiceException;
}
