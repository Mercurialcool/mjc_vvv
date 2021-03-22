package com.epam.esm.service;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.exception.ServiceException;

import java.util.List;

/**
 * Interface for CertificateService entity
 */
public interface CertificateService extends CrudService<CertificateDto> {

    /**
     * Updates Certificate at a service layer
     * @param certificateDto Object
     * @param id ID of a certificate
     * @throws ServiceException
     */
    CertificateDto update(CertificateDto certificateDto, Long id) throws ServiceException;

    /**
     * Searches Certificates by tag
     * @param tagList Object to search by
     * @return List of proper certificates
     * @throws ServiceException
     */
    List<CertificateDto> getCertificateByTag(List<Long> tagList) throws  ServiceException;
}
