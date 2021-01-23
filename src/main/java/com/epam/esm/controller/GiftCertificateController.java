package com.epam.esm.controller;

import com.epam.esm.dao.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.CertificateServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/app")
public class GiftCertificateController {
    private CertificateService certificateService;

    @Autowired
    public void setCertificateService(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public List<Certificate> getAll() throws DaoException {
        try {
            return certificateService.getAll();
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(path = "/addNewCertificate", method = RequestMethod.POST)
    public Certificate create(@RequestBody @Validated Certificate certificate) throws DaoException {
        try {
            return certificateService.add(certificate);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(path = "/removeCertificate", method = RequestMethod.DELETE)
    public boolean removeCertificate(@RequestBody Certificate certificate) throws DaoException {
        try {
            return certificateService.delete(certificate);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(path = "/getCertificateByTag", method = RequestMethod.GET)
    public List<Certificate> getCertificateByTag(@RequestBody Tag tag) throws DaoException {
        try {
            return certificateService.getCertificateByTag(tag);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(path = "/getCertificateByName", method = RequestMethod.GET)
    public List<Certificate> searchCertificateByName(@RequestParam String name) throws DaoException {
        try {
            return certificateService.searchCertificateByName(name);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(path = "/getCertificateByDescription", method = RequestMethod.GET)
    public List<Certificate> searchCertificateByDescription(@RequestParam String description) throws DaoException {
        try {
            return certificateService.searchCertificateByDescription(description);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

}