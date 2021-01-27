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
@RequestMapping("/certificate")
public class GiftCertificateController {
    private CertificateService certificateService;

    @Autowired
    public void GiftCertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Certificate> getAll() throws DaoException {
        try {
            return certificateService.getAll();
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Certificate create(@RequestBody @Validated Certificate certificate) throws DaoException {
        try {
            return certificateService.add(certificate);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean removeCertificate(@RequestBody Certificate certificate) throws DaoException {
        try {
            return certificateService.delete(certificate);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public void updateCertificate(@RequestBody Certificate certificate) throws DaoException {
        try {
            certificateService.update(certificate);
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

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public List<Certificate> search (@RequestParam String template) throws DaoException {
        try {
            return certificateService.search(template);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

}