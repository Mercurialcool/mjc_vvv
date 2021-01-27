package com.epam.esm.service.impl;

import com.epam.esm.dao.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.service.CertificateServiceException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class CertificateServiceImplTest {
    @Autowired
    private CertificateServiceImpl certificateService;
    @Test
    void add() throws DaoException {
        Certificate certificate = new Certificate();
        certificate.setName("test");
        try {
            Certificate createdCertificate = certificateService.add(certificate);
            Assert.assertEquals("test", createdCertificate.getName());
        } catch (CertificateServiceException e) {
            e.printStackTrace();
        }
    }
}