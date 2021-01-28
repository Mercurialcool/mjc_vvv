package com.epam.esm.service;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;

import java.util.List;

public interface CertificateService extends CrudService<Certificate> {
    void update(Certificate certificate) throws  CertificateServiceException;
    List<Certificate> getCertificateByTag(Tag tag) throws  CertificateServiceException;
}
