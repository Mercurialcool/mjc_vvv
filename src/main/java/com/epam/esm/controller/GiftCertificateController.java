package com.epam.esm.controller;

import com.epam.esm.dao.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.CertificateServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {
    private CertificateService certificateService;

    @Autowired
    public void GiftCertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Certificate> getByParameters(@RequestParam(required = false) MultiValueMap<String, String> params) throws DaoException {
        try {
            return certificateService.getByParameters(params);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
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

    @RequestMapping(method = RequestMethod.PUT)
    public void updateCertificate(@RequestBody Certificate certificate) throws DaoException {
        try {
            certificateService.update(certificate);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(path = "/getByTag", method = RequestMethod.GET)
    public List<Certificate> getCertificateByTag(@RequestBody Tag tag) throws DaoException {
        try {
            return certificateService.getCertificateByTag(tag);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }
}