package com.epam.esm.controller;

import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {
    private static final Logger logger = LogManager.getLogger(GiftCertificateController.class);
    private CertificateService certificateService;

    @Autowired
    public GiftCertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * Extracts MultiValueMap to get proper parameters for search query
     * @param
     * @return List of Certificate type
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.GET)
    public List<Certificate> getByParameters(@RequestParam(required = false) MultiValueMap<String, String> params) {
        try {
            return certificateService.getByParameters(params);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Adds new Certificate to DB
     * @param certificate full certificate entity
     * @return Certificate object
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.POST)
    public Certificate create(@RequestBody @Validated Certificate certificate) {
        try {
            return certificateService.add(certificate);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Deletes a Certificate
     * @param certificate certificate object
     * @param id an identifier to perform an operation by
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void removeCertificate(@RequestBody Certificate certificate, @PathVariable Long id) {
        try {
            certificateService.delete(certificate, id);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Updates Certificate by entered fields
     * @param certificate Certificate object
     * @param id An identifier to perform an operation by
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateCertificate(@RequestBody Certificate certificate, @PathVariable Long id) {
        try {
            certificateService.update(certificate, id);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Gets Certificate by Tag
     * @param tag Tag entity
     * @return List of certificates according to tag request
     * @throws DaoException
     */

    @RequestMapping(path = "/getByTag", method = RequestMethod.GET)
    public List<Certificate> getCertificateByTag(@RequestBody Tag tag) {
        try {
            return certificateService.getCertificateByTag(tag);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }
}